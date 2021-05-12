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
public class Revenue {

    private int id;
    private int id_finance;
    private User user;
    private double valeur;
    private String source;
    private String description;
    private String justificatif;
    private Date date;

    public Revenue() {
    }

    public Revenue(int id, int id_finance, User user, double valeur, String source, String description, String justificatif, Date date) {
        this.id = id;
        this.id_finance = id_finance;
        this.user = user;
        this.valeur = valeur;
        this.source = source;
        this.description = description;
        this.justificatif = justificatif;
        this.date = date;
    }

    public Revenue(int id_finance, User user, double valeur, String source, String description, String justificatif) {
        this.id_finance = id_finance;
        this.user = user;
        this.valeur = valeur;
        this.source = source;
        this.description = description;
        this.justificatif = justificatif;
    }

    public Revenue(int id, int id_finance, User user, double valeur, String source, String description, String justificatif) {
        this.id = id;
        this.id_finance = id_finance;
        this.user = user;
        this.valeur = valeur;
        this.source = source;
        this.description = description;
        this.justificatif = justificatif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_finance() {
        return id_finance;
    }

    public void setId_finance(int id_finance) {
        this.id_finance = id_finance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Revenu{" + "id=" + id + ", id_finance=" + id_finance + ", user=" + user + ", valeur=" + valeur + ", source=" + source + ", description=" + description + ", justificatif=" + justificatif + ", date=" + date + '}';
    }

}
