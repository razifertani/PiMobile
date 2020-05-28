package edu.codename1.gui;

import com.codename1.components.ImageViewer;
import com.codename1.facebook.User;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import static edu.codename1.gui.home.current;
import edu.codename1.services.ServicesUsers;

public class Create extends Form {

    Form current;
    private static user User;

    public Create(Resources theme) {
        current = this;
        setTitle("TuniFast");
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        Container cnt = new Container(BoxLayout.y());
        
        ImageViewer imageName = new ImageViewer(theme.getImage("TuniFastLogo.jpg"));
        TextField email = new TextField(null, "Email");
        TextField username = new TextField(null, "Username");
        TextField password = new TextField(null, "Password");
        TextField passwordC = new TextField(null, "Write again your password");
        password.setConstraint(TextField.PASSWORD);
        passwordC.setConstraint(TextField.PASSWORD);

        Button createA = new Button("Create an account");

        cnt.add(imageName);
        cnt.add(email);
        cnt.add(username);
        cnt.add(password);
        cnt.add(passwordC);
        cnt.add(createA);
        add(cnt);

        createA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (!(email.getText().contains("@")) || (username.getText().length() == 0) || (password.getText().length() == 0) || !(password.getText().endsWith(passwordC.getText()))) {
                    if ((email.getText().length() == 0) || (username.getText().length() == 0) || (password.getText().length() == 0)) {
                        Dialog.show("Alert", "Please fill all the fields !", new Command("OK"));
                    } else if (!email.getText().contains("@")) {
                        Dialog.show("Alert", "'Email' should be valid !", new Command("OK"));
                    } else if (!(password.getText() == passwordC.getText())) {
                        Dialog.show("Alert", "Passwords must match !", new Command("OK"));
                    }
                } else {
                    user u = new user(email.getText(), username.getText(), password.getText());
                    if (ServicesUsers.getInstance().addAccount(u)) {
                        Dialog.show("Success", "Accound added !", new Command("OK"));
                        User = ServicesUsers.getInstance().Login(username.getText(), password.getText());
                        Session.start(User);
                        new home(theme).show();
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Login(theme).showBack();
        });

    }
}
