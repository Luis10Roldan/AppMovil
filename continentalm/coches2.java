package com.example.continentalm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class coches2 extends AppCompatActivity {

    private ListView lista;

    Button btnregreso;
    private List<Autos> listAutos;
    private ArrayAdapter<String> adapter;
    private EditText edBuscar;
    private AdapterAutos adapterAutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coches2);
        lista = (ListView) findViewById(R.id.lvLista);
        edBuscar=(EditText)findViewById(R.id.edBuscar);
        leerCatalogo();


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el objeto Autos seleccionado
                Autos autoSeleccionado = listAutos.get(position);
                Intent i=new Intent(getApplicationContext(), detalle.class);
                i.putExtra("precio",autoSeleccionado.getPrecio());
                i.putExtra("descripcion",autoSeleccionado.getDescripcion());
                i.putExtra("foto",autoSeleccionado.getFoto());
                i.putExtra("marca",autoSeleccionado.getMarca());
                i.putExtra("modelo",autoSeleccionado.getModelo());
                startActivity(i);

            }
        });



    }

    private void leerCatalogo(){



        StringRequest stringRequest= new StringRequest(Request.Method.GET, "http://192.168.100.48:80/puntoventaautos/catalogo.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    listAutos = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String descripcion = jsonObject.getString("descripcion");
                        int id_auto = jsonObject.getInt("id_auto");
                        String marca = jsonObject.getString("marca");
                        String modelo = jsonObject.getString("modelo");
                        String precio = jsonObject.getString("precio");
                        String foto = jsonObject.getString("foto");
                        Autos autos = new Autos(descripcion, id_auto, marca, modelo, precio, foto);
                        listAutos.add(autos);
                    }
                    adapterAutos = new AdapterAutos(coches2.this, listAutos);
                    lista.setAdapter(adapterAutos);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error en el JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }

        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    class AdapterAutos extends ArrayAdapter<Autos>{
        AppCompatActivity appCompatActivity;
        List<Autos> listautos;
        AdapterAutos(AppCompatActivity context,List<Autos> listautos){
            super(context,R.layout.auto,listautos);
            appCompatActivity=context;
            this.listautos=listautos;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.auto, null);

            TextView tvPrecio = item.findViewById(R.id.tvDescripcion);
            tvPrecio.setText("$"+listautos.get(position).getPrecio());
            TextView tvDatos = item.findViewById(R.id.datos);
            tvDatos.setText(listautos.get(position).getMarca()+" "+listautos.get(position).getModelo());
            ImageView imageView1 = item.findViewById(R.id.ivAuto);
            TextView descripcion = item.findViewById(R.id.desc);
            descripcion.setText(listautos.get(position).getDescripcion());
            RequestOptions requestOptions=new RequestOptions()
                    .placeholder(R.drawable.uno)
                            .error(R.drawable.dos);
            Glide.with(appCompatActivity).load("http://192.168.100.48:80/puntoventaautos/fotos/"+listautos.get(position).getFoto()).apply(requestOptions).into(imageView1);
            return (item);
        }
    }

}