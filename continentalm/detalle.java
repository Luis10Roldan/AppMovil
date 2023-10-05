package com.example.continentalm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class detalle extends AppCompatActivity {
    private ImageView imageView;
    private TextView desc,prec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        String precio=getIntent().getStringExtra("precio");
        String descripcion=getIntent().getStringExtra("descripcion");
        String foto=getIntent().getStringExtra("foto");
        String marca=getIntent().getStringExtra("marca");
        String modelo=getIntent().getStringExtra("modelo");
        imageView=(ImageView) findViewById(R.id.ivfoto);
        desc=(TextView)findViewById(R.id.tvdescripcion);
        desc.setText(marca+" "+modelo);
        prec=(TextView)findViewById(R.id.tvPrecio);
        prec.setText("$"+precio);
        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.uno)
                .error(R.drawable.dos);
        Glide.with(this).load("http://localhost/prueba/autos/fotos/"+foto).apply(requestOptions).into(imageView);

    }
}