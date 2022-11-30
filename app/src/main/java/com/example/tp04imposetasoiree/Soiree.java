package com.example.tp04imposetasoiree;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Soiree implements Serializable {
    private int id;
    private String libelleCourt;
    private String descriptif;
    private String dateDebut;
    private String heureDebut;
    private String adresse;
    private Double latitude;
    private Double longitude;
    private String login;

    public Soiree() {
    }

    public String afficherDetails(Membre m) {
        LocalDate debut = LocalDate.parse(dateDebut);
        String dateD = debut.format(DateTimeFormatter.ofPattern("d LLLL y").withLocale(Locale.FRANCE));

        LocalTime Hdebut = LocalTime.parse(heureDebut);
        String heureD = Hdebut.format(DateTimeFormatter.ofPattern("H:mm"));

        String detail = descriptif + "\n" + "Date : " + dateD + "\n" +
                "Heure : " + heureD + "\n" + "Soirée déposée par : " + m.getNom() + " " + m.getPrenom();
        return detail;
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
        LocalDate debut = LocalDate.parse(dateDebut);
        String dateD = debut.format(DateTimeFormatter.ofPattern("dd/M/y"));

        return libelleCourt + " " + "(" + dateD + ")" + " - " + login;
    }
}
