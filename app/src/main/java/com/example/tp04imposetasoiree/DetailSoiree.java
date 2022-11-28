package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DetailSoiree extends AppCompatActivity {
    private Membre m ;
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

        Soiree soireeSele = (Soiree) getIntent().getSerializableExtra("soireeSele");

        String loginCreateur = soireeSele.getLogin();
        createAndExecuteGetMembreByLogin(loginCreateur, soireeSele);
        createAndLaunchASWSGetLesParticipants(soireeSele.getId());


    }

    private void createAndLaunchASWSGetLesParticipants(int id) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourGetLesParticipants(s);
            }

        };
        ws.execute("requete=getLesParticipants&soiree="+ id);
    }

    private void traiterRetourGetLesParticipants(String s) {
        Log.d("TRAITER-RETOUR-GETLESPARTICPANTS", s);
        try {
            ObjectMapper oM = new ObjectMapper();
            RetourGetLesParticipants retour =  oM.readValue(s,RetourGetLesParticipants.class);
            List<Membre> membreList = retour.getResponse();
            ((ListView) findViewById(R.id.lvParticipants)).setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, membreList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAndExecuteGetMembreByLogin(String loginCreateur, Soiree soireeSele) {

        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourMembreByLog(s,soireeSele);
            }

        };
        ws.execute("requete=getMembreByLogin&login=" + loginCreateur);
    }

    private void traiterRetourMembreByLog(String s,Soiree soireeSele) {
        Log.d("TRAITER-RETOUR-MEMBRE-BYLOGIN", s);
        try {
           ObjectMapper oM = new ObjectMapper();
         RetourGetMembreByLogin retour =  oM.readValue(s,RetourGetMembreByLogin.class);
         Log.d("retour", retour.getResponse().getNom());
           m = retour.getResponse();
            ((TextView) (findViewById(R.id.tvDetailSoiree))).setText(soireeSele.afficherDetails(m));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
