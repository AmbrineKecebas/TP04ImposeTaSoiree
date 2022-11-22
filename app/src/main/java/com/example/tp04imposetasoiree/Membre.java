package com.example.tp04imposetasoiree;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String ddn;
    private String login;
    private String mdp;
    private String mail;

    public Membre() {
    }

    public Membre(int id, String nom, String prenom, String ddn, String login, String mdp, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.login = login;
        this.mdp = mdp;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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


    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
