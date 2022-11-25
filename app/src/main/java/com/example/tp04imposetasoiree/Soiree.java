package com.example.tp04imposetasoiree;

public class Soiree {
    private String libelle;
    private String descriptif;
    private String date;
    private String heure;
    private String adresse;
    private Double lat;
    private Double lng;


    public Soiree() {
    }

    public Soiree(String libelle, String descriptif, String date, String heure, String adresse, Double lat, Double lng) {
        this.libelle = libelle;
        this.descriptif = descriptif;
        this.date = date;
        this.heure = heure;
        this.adresse = adresse;
        this.lat = lat;
        this.lng = lng;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return libelle + " " + "(" + date + ")";
    }
}
