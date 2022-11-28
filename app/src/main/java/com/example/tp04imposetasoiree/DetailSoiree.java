package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailSoiree extends AppCompatActivity {
    private Button buttonRetour;
    private Button buttonSupSoiree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_soiree);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);

        buttonRetour.setOnClickListener(view -> {
            Intent i = new Intent(this, SoireeAVenir.class);
            startActivity(i);
        });
        buttonSupSoiree = (Button) findViewById(R.id.buttonSupSoiree);
        buttonSupSoiree.setOnClickListener(view -> {
            Intent i = new Intent(this, SoireeAVenir.class);
            startActivity(i);

        });



    }

    private void createAndExecuteDelSoirees(Soiree id) {

        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterDelSoirees(s);
            }

        };
        ws.execute("requete=delSoiree&soiree=" + id.getId());

    }

    private void traiterDelSoirees(String s) {
        Log.d("TRAITER-RETOUR-DELETE-SOIREES", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                Intent i = new Intent(this, SoireeAVenir.class);
                startActivity(i);
                Toast.makeText(this, "Suppression validée", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    }
