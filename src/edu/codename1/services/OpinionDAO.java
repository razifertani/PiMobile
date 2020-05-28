/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.codename1.services;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;

/**
 *
 * @author FERTANI Razi
 */
public class OpinionDAO {
    public OpinionDAO(String nameUser, String nameEvent) {
        Message m = new Message("Hello Mrs/Mr " + nameUser +", you are registred in " + nameEvent + " succefully, we are waiting for you !");
        Display.getInstance().sendMessage(new String[]{"razi.fertani@esprit.tn"}, "Book", m);
    }
}
