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

public class Connexion extends AppCompatActivity {
    private Button buttonIdentifier;
    private Button buttonInscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonIdentifier = (Button) findViewById(R.id.buttonIdentifier);

        buttonIdentifier.setOnClickListener(view -> {
            String login = ((EditText) (findViewById(R.id.etLogin))).getText().toString();

            String mdp = ((EditText) (findViewById(R.id.etPassword))).getText().toString();

            createAndExecuteConnexionMembres(login, mdp);
        });
        buttonInscription = (Button) findViewById(R.id.buttonInscription);
        buttonInscription.setOnClickListener(view -> {
            Intent i = new Intent(this, Inscription.class);
            startActivity(i);
        });



    }

    private void createAndExecuteConnexionMembres(String login, String mdp) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourGetConnexionMembres(s);
            }

        };
        ws.execute("requete=connexion&login=" + login + "&password=" + mdp);

    }


    private void traiterRetourGetConnexionMembres(String s) {
        Log.d("TRAITER-RETOUR-CONNEXION-MEMBRES", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                Intent i = new Intent(this, SoireeAVenir.class);
                startActivity(i);
                Toast.makeText(this, "Connexion", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mauvais login ou mdp", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}





