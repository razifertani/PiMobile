package edu.codename1.gui;

import com.codename1.components.ImageViewer;
import com.codename1.facebook.User;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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

public class Login extends Form {

    Form current;
    private static user User;

    public Login(Resources theme) {
        current = this;
        setTitle("TuniFast");
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        Container cnt = new Container(BoxLayout.y());

        ImageViewer imageName = new ImageViewer(theme.getImage("TuniFastLogo.jpg"));
        TextField username = new TextField(null, "Username");
        TextField password = new TextField(null, "Password");
        password.setConstraint(TextField.PASSWORD);
        Button login = new Button("Login");
        Label l1 = new Label("                     New to this application ?");
        Label l2 = new Label("                     Create an account now !");
        Button createA = new Button("Create an account");

        cnt.add(imageName);
        cnt.add(username);
        cnt.add(password);
        cnt.add(login);
        cnt.add(l1);
        cnt.add(l2);
        cnt.add(createA);
        add(cnt);
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                {
                    if (username.getText().isEmpty() || password.getText().isEmpty()) {
                        Dialog.show("Alert", "Please fill the fields !", "OK", null);
                    } else {
                        User = ServicesUsers.getInstance().Login(username.getText(), password.getText());
                        if (User != null) {
                            Session.start(User);
                            new home(theme).show();
                        } else {
                            Dialog.show("Alert", "Invalid username or password", "OK", null);
                        }
                    }
                }
            }
        });

        createA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new edu.codename1.gui.Create(theme).show();
            }
        });
    }

}
