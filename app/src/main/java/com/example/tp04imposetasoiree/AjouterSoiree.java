package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AjouterSoiree extends AppCompatActivity {
    private Button buttonAddSoir;
    private Button buttonAnnuler2;
    private EditText etDate;
    private EditText etHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_soiree);

        buttonAddSoir = (Button) findViewById(R.id.buttonAddSoir);
        buttonAddSoir.setOnClickListener(view -> {
            String libelleC = ((EditText) (findViewById(R.id.etLibelleCourt))).getText().toString();
            String description = ((EditText) (findViewById(R.id.etDescription))).getText().toString();
            String date = ((EditText) (findViewById(R.id.etDate))).getText().toString();
            String heure = ((EditText) (findViewById(R.id.etHeure))).getText().toString();
            String latitudeString = ((EditText) (findViewById(R.id.etLatitude))).getText().toString();
            Double latitude = Double.parseDouble(latitudeString);
            String Stringlongitude = ((EditText) (findViewById(R.id.etLongitude))).getText().toString();
            Double longitude = Double.parseDouble(Stringlongitude);
            createAndLaunchASWSAddSoirees(libelleC, description, date, heure, latitude, longitude);
        });
        buttonAnnuler2 = (Button) findViewById(R.id.buttonAnnulerAjoutSoiree);
        buttonAnnuler2.setOnClickListener(view -> {
            Intent i = new Intent(this, SoireeAVenir.class);
            startActivity(i);
        });
        etDate = (EditText) findViewById(R.id.etDate);
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        etDate.setFocusable(false);
        etDate.setOnClickListener(view -> {
            DatePickerDialog datePicker = new DatePickerDialog(AjouterSoiree.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int annee, int mois, int jour) {
                    etDate.setText(annee + "-" +( mois + 1)+ "-" + jour);
                }
            }, mYear, mMonth, mDay);
            datePicker.show();


        });
        etHeure = (EditText) findViewById(R.id.etHeure);
        int heure = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        etHeure.setFocusable(false);
        etHeure.setOnClickListener(view -> {
            TimePickerDialog timePicker = new TimePickerDialog(AjouterSoiree.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {
                    etHeure.setText(h + ":" + m);
                }
            }, heure, minute, true);
            timePicker.show();

        });

    }

    private void createAndLaunchASWSAddSoirees(String libelleC, String description, String date, String heure, Double latitude, Double longitude) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourAddSoirees(s);
            }

        };
        ws.execute("requete=addSoiree&libelleCourt=" + libelleC + "&descriptif=" + description +
                "&dateDebut=" + date + "&heureDebut=" + heure + "&latitude=" + latitude + "&longitude=" + longitude);
    }

    private void traiterRetourAddSoirees(String s) {
        Log.d("TRAITER-RETOUR-AJOUT-SOIREES", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                Intent i = new Intent(this, SoireeAVenir.class);
                startActivity(i);
                Toast.makeText(this, "Création de soirée validée", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
