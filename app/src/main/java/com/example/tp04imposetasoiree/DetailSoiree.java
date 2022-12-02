package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private Membre m;
    private Button buttonRetour;
    private Button buttonSupSoiree;
    private Soiree soireeSele;
    private Button buttonInscrireSoiree;
    private Button buttonDesinscrire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_soiree);


//           button.setVisibility(View.VISIBLE); //SHOW the button
//           button.setVisibility(View.INVISIBLE); //HIDE the button

        buttonRetour = (Button) findViewById(R.id.buttonRetour);


        buttonRetour.setOnClickListener(view -> {
            Intent i = new Intent(this, SoireeAVenir.class);
            startActivity(i);
        });
        buttonSupSoiree = (Button) findViewById(R.id.buttonSupSoiree);
        buttonSupSoiree.setOnClickListener(view -> {
            Intent i = new Intent(this, SoireeAVenir.class);
            startActivity(i);
            createAndExecuteDelSoirees(soireeSele.getId());

        });
        buttonInscrireSoiree = (Button) findViewById(R.id.buttonInscrireSoiree);
        buttonInscrireSoiree.setOnClickListener(view -> {
            createAndExecuteInscrire(soireeSele.getId());
        });
        buttonDesinscrire = (Button) findViewById(R.id.buttonDesinscrire);
        buttonDesinscrire.setOnClickListener(view -> {
            createAndExecuteDesinscrire(soireeSele.getId());
        });


        soireeSele = (Soiree) getIntent().getSerializableExtra("soireeSele");

        String loginCreateur = soireeSele.getLogin();
        if (loginCreateur.equalsIgnoreCase(Connexion.login)) {
            buttonDesinscrire.setVisibility(View.INVISIBLE);
            buttonInscrireSoiree.setVisibility(View.INVISIBLE);

        } else {
            buttonSupSoiree.setVisibility(View.INVISIBLE);
        }

        createAndExecuteGetMembreByLogin(loginCreateur, soireeSele);
        createAndLaunchASWSGetLesParticipants(soireeSele.getId());


    }

    private void createAndExecuteDesinscrire(int id) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourDesinscrire(s);
            }

        };
        ws.execute("requete=desinscrire&soiree=" + id);
    }

    private void traiterRetourDesinscrire(String s) {
        Log.d("RETOUR-DESINSCRIRE", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                setResult(RESULT_OK);

                Toast.makeText(this, "Désinscrit.", Toast.LENGTH_SHORT).show();
            } else {
                setResult(RESULT_CANCELED);
                Toast.makeText(this, "Erreur.", Toast.LENGTH_SHORT).show();
            }
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void createAndExecuteInscrire(int id) {

        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourInscrire(s);
            }

        };
        ws.execute("requete=inscrire&soiree=" + id);
    }

    private void traiterRetourInscrire(String s) {
        Log.d("RETOUR-INSCRIRE", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                setResult(RESULT_OK);

                Toast.makeText(this, "Vous êtes inscrit à la soirée.", Toast.LENGTH_SHORT).show();
            } else {
                setResult(RESULT_CANCELED);
                Toast.makeText(this, "Erreur.", Toast.LENGTH_SHORT).show();
            }
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void createAndExecuteGetMembreByLogin(String loginCreateur, Soiree soireeSele) {

        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourMembreByLog(s);
            }

        };
        ws.execute("requete=getMembreByLogin&login=" + loginCreateur);
    }

    private void traiterRetourMembreByLog(String s) {
        Log.d("TRAITER-RETOUR-GETMEMBRESBYLOG", s);
        try {
            ObjectMapper oM = new ObjectMapper();
            RetourGetMembreByLogin retour = oM.readValue(s, RetourGetMembreByLogin.class);
            Log.d("retour", retour.getResponse().getNom());
            m = retour.getResponse();
            ((TextView) (findViewById(R.id.tvDetailSoiree))).setText(soireeSele.afficherDetails(m));
            ((TextView) (findViewById(R.id.tvLibelleCourt))).setText(soireeSele.getLibelleCourt());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createAndLaunchASWSGetLesParticipants(int id) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterRetourGetLesParticipants(s);
            }

        };
        ws.execute("requete=getLesParticipants&soiree=" + id);
    }

    private void traiterRetourGetLesParticipants(String s) {
        Log.d("TRAITER-RETOUR-GETLESPARTICPANTS", s);
        try {
            ObjectMapper oM = new ObjectMapper();
            RetourGetLesParticipants retour = oM.readValue(s, RetourGetLesParticipants.class);
            List<Membre> membreList = retour.getResponse();
            ((ListView) findViewById(R.id.lvParticipants)).setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, membreList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void traiterRetourGetParticipants(String s) {
        Log.d("TRAITER-RETOUR-GET-PARTICIPANTS", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void createAndLaunchASWSGetParticipants(Soiree idSoiree) {
        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterRetourGetParticipants(s);
            }
        };
        ws.execute("requete=getLesParticipants&soiree=" + idSoiree.getId());
    }


    private void createAndExecuteDelSoirees(int id) {

        WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                traiterDelSoirees(s);
            }

        };
        ws.execute("requete=delSoiree&soiree=" + id);

    }

    private void traiterDelSoirees(String s) {
        Log.d("TRAITER-RETOUR-DELETE-SOIREES", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getBoolean("success")) {
                setResult(RESULT_OK);

                Toast.makeText(this, "La soirée a bien été supprimée.", Toast.LENGTH_SHORT).show();
            } else {
                setResult(RESULT_CANCELED);
                Toast.makeText(this, "Erreur de suppression.", Toast.LENGTH_SHORT).show();
            }
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    {

    }
}
