/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ALL TEC
 */
public class Reclamation {
        private int id;
        private int idUtilisateur;
        private String sujet;
        private String comment;
        private Date date;
        private String etat;
        private User user;


    public Reclamation() {
    }

    public Reclamation(int id, int idUtilisateur, String sujet, String comment) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.sujet = sujet;
        this.comment = comment;
    }
    
     public Reclamation( int idUtilisateur, String sujet, String comment) {
        this.idUtilisateur = idUtilisateur;
        this.sujet = sujet;
        this.comment = comment;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idUtilisateur=" + idUtilisateur + ", sujet=" + sujet + ", comment=" + comment + ", date=" + date + ", etat=" + etat + '}';
    }
        
        
        
        
        
        
        
        

}
