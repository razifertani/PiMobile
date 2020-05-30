package edu.codename1.gui;

import com.codename1.components.ImageViewer;
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
import edu.codename1.entities.Session;
import edu.codename1.entities.user;
import edu.codename1.services.ServicesEvents;
import edu.codename1.services.ServicesUsers;
import java.util.ArrayList;

public class Events extends Form {

    static Form current;
    user User = Session.getCurrentSession();

    public Events(Resources theme) {
        current = this;

        setTitle("Events");
        setLayout(BoxLayout.y());
        Container cnt = new Container(BoxLayout.y());

        ArrayList<Event> listEvent = ServicesEvents.getInstance().getListEvent();
        for (int i = 0; i < listEvent.size(); i++) {
            if (listEvent.get(i).getId() != 0 && !listEvent.get(i).getDepart().trim().equals("") && !listEvent.get(i).getDestination().trim().equals("") && !listEvent.get(i).getNom().trim().equals("") && !listEvent.get(i).getDate().trim().equals("") && listEvent.get(i).getNbr() != 0) {
                Event cs = listEvent.get(i);
            }
        }
        
        for (Event i : listEvent) {
            if (i.getNbr() != 0) {
                Label lb = new Label(i.getNom() + " at " + i.getDestination());
                cnt.add(lb);

                Button detail = new Button("View details");
                cnt.add(detail);
                cnt.add(" ");

                detail.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        {
                            new detailEvent(theme, i).show();
                        }
                    }
                });
            }
        }

        add(cnt);

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new home(theme).showBack();
        });

    }
}
