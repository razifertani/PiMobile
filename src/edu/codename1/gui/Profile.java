package edu.codename1.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.codename1.entities.Session;
import edu.codename1.entities.user;
import static edu.codename1.gui.home.current;


public class Profile extends Form {

    static Form currentForm;
    user User = Session.getCurrentSession();

    public Profile(Resources theme) {
        currentForm = this;
        setTitle("Profile");
        setLayout(BoxLayout.y());
        Container cnt = new Container(BoxLayout.y());

        Label l1 = new Label("Username: " + User.getUsername());
        Label l2 = new Label("Email: " + User.getEmail());
        Label l3 = new Label("Rôle: " + User.getRoles());

        cnt.add(l1);
        cnt.add(l2);
        cnt.add(l3);
        add(cnt);

        getToolbar().addCommandToOverflowMenu("Edit Profile", null, ev -> {
            new EditProfile(theme).show();
        });

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new home(theme).showBack();
        });
    }
}
