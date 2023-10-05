package com.example.continentalm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class adaptador extends BaseAdapter {



    private static LayoutInflater inflater = null;

    Context contexto;
    List<Autos>datos;
    int [] datosImg;

    Button btnregreso;




    public adaptador(Context conexto, List<Autos> datos, int [] imagenes){
        this.contexto = conexto;
        this.datos=datos;
        this.datosImg=imagenes;
        inflater = (LayoutInflater) conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final  View vista = inflater.inflate(R.layout.elementolis,null);
        TextView titulo = (TextView)  vista.findViewById(R.id.tvTitulo);
        TextView duracion = (TextView)  vista.findViewById(R.id.tvDuracion);
        TextView director = (TextView)  vista.findViewById(R.id.tvDirector);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imageView7);
        btnregreso = (Button) vista.findViewById(R.id.btnregreso);
        TextView des = (TextView) vista.findViewById(R.id.descrip);

        Autos autos=datos.get(i);

        imagen.setImageResource(R.drawable.bien);
        des.setText(autos.getDescripcion());


        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent visorImagen = new Intent(contexto,VisorImagen.class);
                visorImagen.putExtra("IMG",datosImg[(Integer)v.getTag()]);
                contexto.startActivity(visorImagen);

            }
        });

        btnregreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto,detalle.class);
                contexto.startActivity(i);
            }
        });

        return vista;





    }







    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Autos getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



}
