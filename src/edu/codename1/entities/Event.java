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
public class Event {
    int id;
    String nom;
    String destination;
    String depart;
    String date;
    int nbr;

    public Event() {
    }
    
    public Event(int id, String nom, String destination, String depart, String date, int nbr) {
        this.id = id;
        this.nom = nom;
        this.destination = destination;
        this.depart = depart;
        this.date = date;
        this.nbr = nbr;
    }
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepart() {
        return depart;
    }

    public String getDate() {
        return date;
    }

    public int getNbr() {
        return nbr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final Event other = (Event) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event:" + nom + ", Destination=" + destination + ", Departure=" + depart + ", Date=" + date + ", Number of places =" + nbr;
    }
    
   
    
}
