package org.example;

public class Utilisateur {

    public  String nom,prenom,email;
    int id;

    public Utilisateur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int i) {
        this.id=i;
    }

    public int getId() {
        return id;
    }
}
