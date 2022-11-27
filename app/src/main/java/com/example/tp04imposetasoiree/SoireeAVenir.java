package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    private void traiterRetourGetSoirees(String retour) {
        Log.d("TRAITER-RETOUR-GETSOIREES", retour);

        try {
           ObjectMapper objetMapper =  new ObjectMapper();
           RetourGetSoiree soirees = objetMapper.readValue(retour, RetourGetSoiree.class);

           List<Soiree> soireeList = soirees.getResponse() ;

            ((ListView) findViewById(R.id.lvSoirees)).setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, soireeList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


