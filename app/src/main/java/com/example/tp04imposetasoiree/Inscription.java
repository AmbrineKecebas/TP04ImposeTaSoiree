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

public class Inscription extends AppCompatActivity {
    private Button buttonValider;
    private Button buttonAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(view -> {
            String login = ((EditText) (findViewById(R.id.etLog2))).getText().toString();
            String nom = ((EditText) (findViewById(R.id.etNom))).getText().toString();
            String mdp = ((EditText) (findViewById(R.id.etMdp1))).getText().toString();
            String prenom = ((EditText) (findViewById(R.id.etPrenom))).getText().toString();
            String ddn = ((EditText) (findViewById(R.id.etDdn))).getText().toString();
            String mail = ((EditText) (findViewById(R.id.etMail))).getText().toString();
            createAndExecuteInscriptionMembres(login, mdp, nom, prenom, ddn, mail);
        });
        buttonAnnuler = (Button) findViewById(R.id.buttonAnnuler);
        buttonAnnuler.setOnClickListener(view -> {
            Intent i = new Intent(this, Connexion.class);
            startActivity(i);
        });

    }

    private void createAndExecuteInscriptionMembres(String login, String mdp, String nom, String prenom, String ddn, String mail) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourGetInscriptionMembres(s);
            }

        };
        ws.execute("requete=creerCompte&login=" + login + "&nom=" + nom + "&prenom=" + prenom + "&ddn=" + ddn + "&mail=" + mail + "&password=" + mdp);

    }

    private void traiterRetourGetInscriptionMembres(String s) {
        Log.d("TRAITER-RETOUR-INSCRIPTION-MEMBRES", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {

                Toast.makeText(this, "Inscription validée", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
