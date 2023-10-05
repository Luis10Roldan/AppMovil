package com.example.continentalm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class PrimeraPantalla extends MainActivity {

    ImageButton  botonAcceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);
        botonAcceso=(ImageButton) findViewById(R.id.botonAcceso);
        botonAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}