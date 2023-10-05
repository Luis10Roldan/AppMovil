package com.example.continentalm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class VisorImagen extends AppCompatActivity {

    Button btnregreso;
    Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_imagen);

        btnregreso = (Button) findViewById(R.id.uno);

         ImageView edt = (ImageView) findViewById(R.id.tres);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b!=null){

            edt.setImageResource(b.getInt("IMG"));
        }

        btnregreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),detalle.class);
                startActivity(i);
            }
        });
    }


}