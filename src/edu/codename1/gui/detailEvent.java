/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.codename1.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.codename1.entities.Event;
import edu.codename1.entities.Inscription;
import edu.codename1.entities.Session;
import edu.codename1.entities.user;
import static edu.codename1.gui.Events.current;
import edu.codename1.services.OpinionDAO;
import edu.codename1.services.ServicesInscription;
import edu.codename1.services.TwilioSMS;

/**
 *
 * @author FERTANI Razi
 */
public class detailEvent extends Form {

    Form current;
    user User = Session.getCurrentSession();

    public detailEvent(Resources theme, Event event) {
        current = this;
        Container cnt = new Container(BoxLayout.y());

        setTitle(event.getNom());

        cnt.add(" ");
        cnt.add("at " + event.getDestination());
        cnt.add(" ");
        cnt.add("from " + event.getDepart());
        cnt.add(" ");
        cnt.add("" + event.getDate());
        cnt.add(" ");

        Label lbp = new Label("Number of places remaining: " + event.getNbr() + "        ");
        cnt.add(lbp);
        cnt.add(" ");

        Button book = new Button("Book");
        cnt.add(book);

        add(cnt);

        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean e = Dialog.show("Book", "Do you really want to book ? ", "Yes", "No");
                if (e) {
                    Inscription i = new Inscription();
                    user c = new user();
                    c.setId(User.getId());
                    i.setIdClient(c);
                    int nbr = event.getNbr();
                    if (nbr == 0) {
                        Dialog.show("Alert", "No places remaining, Sorry !", "Ok", null);
                    } else {
                        nbr--;
                        event.setNbr(nbr);
                        i.setIdEvent(event);

//                    new OpinionDAO(User.getUsername(),event.getNom()); 
/*
                    User.toString();
                    TwilioSMS s = new TwilioSMS("AC074fe3915843dd08870c671c756c753c", "4bf351cd08cb34c6fc472ca017efba94", "+13344876169");
                    s.sendSmsAsync("+21658116113", "Mrs,Mr you are registred in :" + event.getNom() + "  succefully" + User.getUsername() + "Welcome, we are waiting for you !");
                         */
                        ServicesInscription.getInstance().addInscription(i);
                        Dialog.show("SUCCESS", "You are now registred !", "Ok", null);
                    }
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Events(theme).showBack();
        });

    }

}
