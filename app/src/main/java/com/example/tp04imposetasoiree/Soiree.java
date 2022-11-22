package com.example.tp04imposetasoiree;

public class Soiree {
    private String libelle;
    private String descriptif;
    private String date;
    private String heure;
    private String adresse;

    public Soiree() {
    }

    public Soiree(String libelle, String descriptif, String date, String heure, String adresse) {
        this.libelle = libelle;
        this.descriptif = descriptif;
        this.date = date;
        this.heure = heure;
        this.adresse = adresse;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
