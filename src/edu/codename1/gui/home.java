package edu.codename1.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.codename1.entities.Session;
import edu.codename1.entities.user;


public class home extends Form {

    static Form current;
    user User = Session.getCurrentSession();

    public home(Resources theme) {
        current = this;

//        setTitle(User.getRoles());
        setTitle("Welcome to TuniFast");
        setLayout(BoxLayout.y());
        Container cnt = new Container(BoxLayout.y());
        
        Label l = new Label("Hello " + User.getUsername());
        cnt.add(l);
        add(cnt);
        
        
        getToolbar().addCommandToSideMenu("Home", null, ev -> {
        });

        getToolbar().addCommandToSideMenu("Profile", null, ev -> {
            new Profile(theme).show();
        });

        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, ev -> {
            try {
                Session.close();
                new Login(theme).showBack();
            } catch (Exception ex) {
                ex.getMessage();
            }
        });
    }
}
