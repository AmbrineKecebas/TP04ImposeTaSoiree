package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SoireeAVenir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        createAndLaunchASWSGetSoirees();
        ((ListView) findViewById(R.id.lvSoirees)).setOnItemLongClickListener((adapterView, view, i, l) -> {
            Soiree soireeSel = (Soiree) adapterView.getItemAtPosition(i);
            return true;
        });
    }

    private void onItemLongClickLV(Soiree soireeSel) {
    }

    private void createAndLaunchASWSGetSoirees() {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourGetSoirees(s);
            }
        };
        ws.execute("requete=getLesSoirees");
    }

    private void traiterRetourGetSoirees(String s) {
        Log.d("TRAITER-RETOUR-GETSOIREES", s);
        List<Soiree> soireeList = new ArrayList<>();
        try {
            JSONArray jsona = new JSONArray(s);
            for (int i = 0; i < jsona.length(); i++) {
                JSONObject jsono = jsona.getJSONObject(i);
                String libelle = jsono.getString("libelle");
                String descriptif = jsono.getString("descriptif");
                String date = jsono.getString("date");
                String heure = jsono.getString("heure");
                String adresse = jsono.getString("adresse");
                Double lat = jsono.getDouble("lat");
                Double lng = jsono.getDouble("lng");
                Soiree soir = new Soiree(libelle, descriptif, date, heure, adresse, lat, lng);
                soireeList.add(soir);
            }
            ((ListView) findViewById(R.id.lvSoirees)).setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, soireeList));
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
}


