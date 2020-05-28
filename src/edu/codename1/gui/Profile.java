package edu.codename1.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.codename1.entities.Session;
import edu.codename1.entities.user;
import static edu.codename1.gui.home.current;
import edu.codename1.services.ServicesUsers;

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
        Label l4 = new Label("You are a CLIENT until now, you want to");
        Label l5 = new Label("become a driver ?");
        Button upgrade = new Button("Upgrade now");

        cnt.add(l1);
        cnt.add(l2);
        cnt.add(l3);
        if (!User.getRoles().contains("LIVREUR")) {
            cnt.add(l4);
            cnt.add(l5);
            cnt.add(upgrade);
        }
        add(cnt);

        getToolbar().addCommandToOverflowMenu("Edit password", null, ev -> {
            new EditPassword(theme).show();
        });
        
        getToolbar().addCommandToOverflowMenu("Desactivate Profile", null, ev -> {
            boolean test = Dialog.show("Alert", "Are you sure about desactivating your account ? This action is irreversible", "OK", "Cancel");
            if (test) {
                try {
                    ServicesUsers.getInstance().deleteM(User.getUsername());
                    Session.close();
                    new Login(theme).showBack();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new home(theme).showBack();
        });

        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                {
                    boolean test = ServicesUsers.getInstance().updateMR(User.getUsername());
                    if (test) {
                        Dialog.show("Success", "You become a driver !", "OK", null);
                        new Profile(theme).show();
                    } else {
                        Dialog.show("Alert", "Role didn't changed", "OK", null);
                    }
                }
            }
        });

    }
}
