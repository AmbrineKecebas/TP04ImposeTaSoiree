package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SoireeAVenir extends AppCompatActivity {
    private Button buttonAddSoiree;
    private Button buttonDeconnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        createAndLaunchASWSGetSoirees();
        ((ListView) findViewById(R.id.lvSoirees)).setOnItemLongClickListener((adapterView, view, i, l) -> {
            Soiree soireeSel = (Soiree) adapterView.getItemAtPosition(i);
            onItemLongClickLV(soireeSel);
            return true;
        });
        ((ListView) findViewById(R.id.lvSoirees)).setOnItemClickListener((adapterView, view, i, l) -> {
            Soiree soireeSel = (Soiree) adapterView.getItemAtPosition(i);
            onItemClickListener(soireeSel);
        });

        buttonAddSoiree = (Button) findViewById(R.id.buttonAddSoiree);
        buttonAddSoiree.setOnClickListener(view -> {
            Intent i = new Intent(this, AjouterSoiree.class);
            startActivity(i);
        });
        buttonDeconnexion = (Button) findViewById(R.id.buttonDeconnexion);
        buttonDeconnexion.setOnClickListener(view -> {
            Intent i = new Intent(this, Connexion.class);
            startActivity(i);
        });
//createAndLaunchASWSDecMembres();
    }

    private void onItemClickListener(Soiree soireeSel) {
        Intent i = new Intent(this, DetailSoiree.class);
        startActivity(i);
    }

    private void onItemLongClickLV(Soiree soireeSel) {
        createAndLaunchASWSDelSoirees(soireeSel);
    }

    private void createAndLaunchASWSDelSoirees(Soiree soireeSel) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourDelSoirees(s);
            }
        };
        ws.execute("requete=delSoiree&soiree=" + soireeSel.getId());
    }


    private void traiterRetourDelSoirees(String s) {
        if (s.equals("1")) {
            Toast.makeText(this, "Suppression soirée", Toast.LENGTH_SHORT).show();
            createAndLaunchASWSGetSoirees();
        } else {
            Toast.makeText(this, "Erreur ", Toast.LENGTH_SHORT).show();
        }
    }


    /**private void createAndLaunchASWSDecMembres() {
     WSConnexionHTTPS ws = new WSConnexionHTTPS() {
    @Override protected void onPostExecute(String s) {
    traiterRetourDecMembres(s);
    }
    };
     ws.execute("requete=deconnexion");
     }**/

    /**
     * private void traiterRetourDecMembres(String s) {
     * Log.d("TRAITER-RETOUR-DECMEMBRES", s);
     * try {
     * JSONObject jsonObject = new JSONObject(s);
     * if (jsonObject.getBoolean("success")) {
     * Toast.makeText(this, "Vous êtes déconnecté.", Toast.LENGTH_SHORT).show();
     * } else {
     * Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
     * }
     * } catch (JSONException e) {
     * e.printStackTrace();
     * }
     * }
     **/

    private void createAndLaunchASWSGetSoirees() {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourGetSoirees(s);
            }
        };
        ws.execute("requete=getLesSoirees");
    }

    private void traiterRetourGetSoirees(String retour) {
        Log.d("TRAITER-RETOUR-GETSOIREES", retour);

        try {
            ObjectMapper objetMapper = new ObjectMapper();
            RetourGetSoiree soirees = objetMapper.readValue(retour, RetourGetSoiree.class);

            List<Soiree> soireeList = soirees.getResponse();

            ((ListView) findViewById(R.id.lvSoirees)).setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, soireeList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


