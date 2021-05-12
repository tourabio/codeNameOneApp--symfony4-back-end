/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ALL TEC
 */
public class User {
    private int id_User;
    private String name;
    private String familyName;
    private String type;
    private String email;
    private String password;
    private String subject;
    private String classe;
    
    public User() {
    }

    public User(int id_User, String name, String familyName, String type, String email, String password, String subject, String classe) {
        this.id_User = id_User;
        this.name = name;
        this.familyName = familyName;
        this.type = type;
        this.email = email;
        this.password = password;
        this.subject = subject;
        this.classe = classe;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "User{" + "id_User=" + id_User + ", name=" + name + ", familyName=" + familyName + ", type=" + type + ", email=" + email + ", password=" + password + ", subject=" + subject + ", classe=" + classe + '}';
    }
    
    
    
    
    
    
    
    
    
    
}
