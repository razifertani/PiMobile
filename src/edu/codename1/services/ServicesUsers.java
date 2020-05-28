package edu.codename1.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.codename1.entities.user;
import static edu.codename1.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.Map;


public class ServicesUsers {

    public static ServicesUsers instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public user User = new user();

    private ServicesUsers() {
        req = new ConnectionRequest();  
    }

    public static ServicesUsers getInstance() {

        if (instance == null) {
            instance = new ServicesUsers();
        }
        return instance;
    }

    public boolean addAccount(user u) {
        String url = "http://127.0.0.1:8000/registerAPI/" + u.getEmail() + "/" + u.getUsername() + "/" + u.getPassword();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public user parseUser(String jsonText) {

        user UserL = new user();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if (UserListJson.get("type").equals("Login succeed")) {
                float id = Float.parseFloat(UserListJson.get("id").toString());
                UserL.setId((int) (id));
                UserL.setEmail(UserListJson.get("email").toString());
                UserL.setUsername(UserListJson.get("username").toString());
                if (UserListJson.get("role").toString().contains("ROLE_LIVREUR")) {
                    UserL.setRoles("LIVREUR");
                } else {
                    UserL.setRoles("CLIENT");
                }
            } else {
                return null;
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        return UserL;
    }

    public user Login(String username, String password) {
        String url = BASE_URL + "loginMobile/" + username + "/" + password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }

    
    public boolean updateMP(String username, String password) {
        String url = BASE_URL + "updateAPIP/" + username + "/" + password;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateMR(String username) {
        String url = BASE_URL + "updateAPIR/" + username;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
    public boolean updateMC(String username) {
        String url = BASE_URL + "updateAPIC/" + username;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     

    public void deleteM(String username) {
        req.setPost(true);
        String url = BASE_URL + "deleteAPI/" + username;
        req.setUrl(url);
        req.setHttpMethod("POST");
        req.addArgument("User", String.valueOf(username));
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    


}
