package com.example.continentalm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class princi2 extends AppCompatActivity {

    Button botonAcceso2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princi2);
        botonAcceso2=(Button) findViewById(R.id.botonAcceso2);
        botonAcceso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),coches2.class);
                startActivity(i);
            }
        });




    }
}