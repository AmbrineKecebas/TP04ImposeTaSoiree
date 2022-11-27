package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AjouterSoiree extends AppCompatActivity {
private Button buttonAddSoir ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_soiree);

        buttonAddSoir = (Button) findViewById(R.id.buttonAddSoir);
         buttonAddSoir.setOnClickListener(view ->{
             String libelleC = ((EditText) (findViewById(R.id.etLibelleCourt))).getText().toString();
             String description = ((EditText) (findViewById(R.id.etDescription))).getText().toString();
             String date = ((EditText) (findViewById(R.id.etDate))).getText().toString();
             String heure = ((EditText) (findViewById(R.id.etHeure))).getText().toString();
             String latitudeString = ((EditText) (findViewById(R.id.etLatitude))).getText().toString();
             Double latitude = Double.parseDouble(latitudeString);
             String Stringlongitude = ((EditText) (findViewById(R.id.etLongitude))).getText().toString();
            Double longitude = Double.parseDouble(Stringlongitude);
             createAndLaunchASWSAddSoirees(libelleC, description,date,heure,latitude,longitude);
        });
    }

    private void createAndLaunchASWSAddSoirees(String libelleC,String description,String date,String heure,Double latitude, Double longitude) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourAddSoirees(s);
            }

        };
        ws.execute("requete=addSoiree&libelleCourt="+libelleC+"&descriptif="+ description+
                "&dateDebut="+date+"&heureDebut="+ heure+"&latitude="+ latitude+ "&longitude="+ longitude);
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
