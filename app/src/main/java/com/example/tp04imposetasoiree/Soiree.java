package com.example.tp04imposetasoiree;

import java.io.Serializable;

public class Soiree implements Serializable {
    private int id ;
    private String libelleCourt;
    private String descriptif;
    private String dateDebut;
    private String heureDebut;
    private String adresse;
    private Double latitude;
    private Double longitude;
    private String login ;

    public Soiree() {
    }
    public String afficherDetails(Membre m){
        String detail = libelleCourt + " " + descriptif + " " + dateDebut + " " + heureDebut + " " + m.getNom() + " " + m.getPrenom() ;
        return detail ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleCourt() {
        return libelleCourt;
    }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return libelleCourt + " "+ "("+ dateDebut + ")" ;
    }
}
