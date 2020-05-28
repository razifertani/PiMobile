/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.codename1.entities;

import java.util.Objects;

/**
 *
 * @author FERTANI Razi
 */
public class Inscription {
    int id;
    Event idEvent;
    user idClient;

    public Inscription() {
    }

    
    public Inscription(int id, Event idEvent, user idClient) {
        this.id = id;
        this.idEvent = idEvent;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public user getIdClient() {
        return idClient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public void setIdClient(user idClient) {
        this.idClient = idClient;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.idEvent);
        hash = 53 * hash + Objects.hashCode(this.idClient);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inscription other = (Inscription) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.idEvent, other.idEvent)) {
            return false;
        }
        if (!Objects.equals(this.idClient, other.idClient)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", idEvent=" + idEvent + ", idClient=" + idClient + '}';
    }

    
    
}
