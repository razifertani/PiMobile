package edu.codename1.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.codename1.entities.Session;
import edu.codename1.entities.user;
import edu.codename1.services.ServicesUsers;

public class EditPassword extends Form {

    static Form currentForm;
    user User = Session.getCurrentSession();

    public EditPassword(Resources theme) {
        
        currentForm = this;
        setTitle("Edit Profile");
        setLayout(BoxLayout.y());
        Container cnt = new Container(BoxLayout.y());

        Label l1 = new Label("Set your current password:");
        TextField t1 = new TextField(null, "Current password");

        Label l2 = new Label("Set your new password:");
        TextField t2 = new TextField(null, "New password");

        Label l3 = new Label("Rewrite your new password:");
        TextField t3 = new TextField(null, "New password");

        Button change = new Button("Change my password");

        cnt.add(l1);
        cnt.add(t1);
        cnt.add(l2);
        cnt.add(t2);
        cnt.add(l3);
        cnt.add(t3);
        cnt.add(change);
        add(cnt);

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                {
                    if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || User.getPassword() != t1.getText() || t2.getText() != t3.getText()) {
                        if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty()) {
                            Dialog.show("Alert", "Please fill the fields !", "OK", null);
                        } else if (!User.getUsername().endsWith(t1.getText())) {
                            Dialog.show("Alert", "Please check the current password !", "OK", null);
                        } else if (!t2.getText().endsWith(t3.getText())) {
                            Dialog.show("Alert", "Passwords must match !", "OK", null);
                        }
                    } else {
                        System.out.println("Houni");
                        boolean test = ServicesUsers.getInstance().updateMP(User.getUsername(), t2.getText());
                        if (test) {
                            new Profile(theme).showBack();
                        } else {
                            Dialog.show("Alert", "Impossible to change password", "OK", null);
                        }
                    }
                }
            }
        }
        );

        getToolbar()
                .addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
                    new Profile(theme).showBack();
                }
                );
    }
}
