package com.example.tp04imposetasoiree;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoMembre {
    private static DaoMembre instance = null;
    private final List<Membre> membres;
    private final ArrayAdapter<Membre> arrayAdapterMembre;
    private final Context context;
    private final ObjectMapper om = new ObjectMapper();

    private DaoMembre(Context context) {
        this.context = context;
        membres = new ArrayList<>();
        arrayAdapterMembre = new ArrayAdapter(context, android.R.layout.simple_list_item_1, membres);
        refreshListMembre();
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public ArrayAdapter<Membre> getArrayAdapterMembre() {
        return arrayAdapterMembre;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new DaoMembre(context);
        }
    }

    public static DaoMembre getInstance() {
        return instance;
    }


    private void refreshListMembre() {
        String url = "requete=getMembreByLogin&login=" ;
        WSConnexion wsConnexion = new WSConnexion() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourGetMembres(s);
            }
        };

        wsConnexion.execute(url);
    }

    private void traiterRetourGetMembres(String s) {

        try {
            membres.clear();
            Arrays.asList(om.readValue(s, Membre[].class)).forEach(membre -> membres.add(membre));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        arrayAdapterMembre.notifyDataSetChanged();
    }
}

