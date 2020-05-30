/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.codename1.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.codename1.entities.Event;
import static edu.codename1.utils.Statics.BASE_URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author FERTANI Razi
 */
public class ServicesEvents {

    public ArrayList<Event> event;
    private ConnectionRequest req;
    public static ServicesEvents instance = null;
    public String retour = "";

    public ServicesEvents() {
        req = new ConnectionRequest();
    }

    public static ServicesEvents getInstance() {
        if (instance == null) {
            instance = new ServicesEvents();
        }
        return instance;
    }

    public ArrayList<Event> parseEvent(String jsonText) {
        try {
            event = new ArrayList<>();
            JSONObject obj = new JSONObject(jsonText);
            JSONArray arr = obj.getJSONArray("liste");
            for (int i = 0; i < arr.length(); i++) {
                Event ch = new Event();
                ch.setId(arr.getJSONObject(i).getInt("id"));
                ch.setNom(arr.getJSONObject(i).getString("nom"));
                ch.setDepart(arr.getJSONObject(i).getString("depart"));
                ch.setDestination(arr.getJSONObject(i).getString("destination"));
                ch.setNbr(arr.getJSONObject(i).getInt("nbr"));
                ch.setDate(arr.getJSONObject(i).getString("date"));

                event.add(ch);
            }
        } catch (JSONException ex) {
            System.out.println("Erreur");
        }
        return event;
    }

    public ArrayList<Event> getListEvent() {
        String url = BASE_URL + "Event/showE";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                event = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;
    }
    


}
