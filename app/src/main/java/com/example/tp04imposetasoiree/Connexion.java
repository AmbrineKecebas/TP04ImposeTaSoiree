package com.example.tp04imposetasoiree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Connexion extends AppCompatActivity implements View.OnClickListener {
    private Button buttonIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonIdentifier = (Button) findViewById(R.id.buttonIdentifier);
        buttonIdentifier.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Inscription.class);
        Connexion.this.startActivity(i);
        buttonIdentifier = (Button) findViewById(R.id.buttonIdentifier);
        buttonIdentifier.setOnClickListener(this);

    }

}


