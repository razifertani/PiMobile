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
import edu.codename1.entities.Inscription;
import static edu.codename1.utils.Statics.BASE_URL;

/**
 *
 * @author FERTANI Razi
 */
public class ServicesInscription {

    public static ServicesInscription instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Boolean reponse;

    public ServicesInscription() {
        req = new ConnectionRequest();
    }

    public static ServicesInscription getInstance() {

        if (instance == null) {
            instance = new ServicesInscription();
        }
        return instance;
    }

    public Boolean addInscription(Inscription i) {

        String url = BASE_URL + "Event/inscription/" + i.getIdEvent().getId() + "/" + i.getIdClient().getId() + "/newM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reponse;
    }

}
