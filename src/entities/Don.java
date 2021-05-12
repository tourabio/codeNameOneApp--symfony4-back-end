/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus_pc
 */
public class Don {
    private int id;
    private String nom;
    private String prenom;
    private int telephone;
    private String adresse;
    private String typeDons;
    private int qtt;

    public Don() {
    }

    public Don(int id, String nom, String prenom, int telephone, String adresse, String typeDons, int qtt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.typeDons = typeDons;
        this.qtt = qtt;
    }
     public Don(String nom, String prenom, int telephone, String adresse, String typeDons, int qtt) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.typeDons = typeDons;
        this.qtt = qtt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeDons() {
        return typeDons;
    }

    public void setTypeDons(String typeDons) {
        this.typeDons = typeDons;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", adresse=" + adresse + ", typeDons=" + typeDons + ", qtt=" + qtt + '}';
    }
    
}
