package com.example.continentalm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class coches extends AppCompatActivity {

    ImageButton btnfiltro;
    EditText etBuscarMarca;
    ListView lvResultados;
    ArrayAdapter<String> adapter;
    List<String> marcas;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coches);

        btnfiltro=(ImageButton) findViewById(R.id.btnfiltro) ;
        btnfiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialogoMarcas();
            }
        });
        etBuscarMarca = findViewById(R.id.etBuscarMarca);
        lvResultados = findViewById(R.id.lvResultados);

        // Crear una lista de marcas (puedes personalizarla con tus marcas específicas)
        marcas = new ArrayList<>();
        marcas.add("Toyota");

        marcas.add("Honda");
        marcas.add("Ford");
        marcas.add("Chevrolet");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, marcas);
        lvResultados.setAdapter(adapter);

        etBuscarMarca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filtrarMarcas(s.toString());
            }
        });
    }

    public class CustomAdapter extends ArrayAdapter<String> {

        private List<String> items;
        private Context context;

        public CustomAdapter(Context context, List<String> items) {
            super(context, 0, items);
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(context).inflate(R.layout.activity_coches, parent, false);
            }

            String currentItem = items.get(position);

            ImageView imageView = listItemView.findViewById(R.id.imageView);
            // Asignar imagen personalizada para cada elemento
            // Por ejemplo: imageView.setImageResource(R.drawable.mi_imagen);

            TextView textView = listItemView.findViewById(R.id.textView);
            textView.setText(currentItem);

            return listItemView;
        }
    }

    private void filtrarMarcas(String filtro) {
        List<String> marcasFiltradas = new ArrayList<>();

        for (String marca : marcas) {
            if (marca.toLowerCase().contains(filtro.toLowerCase()) && marca.equalsIgnoreCase("Toyota")) {
                marcasFiltradas.add(marca);
            }
        }

        adapter.clear();
        adapter.addAll(marcasFiltradas);
        adapter.notifyDataSetChanged();
    }
    private void abrirDialogoMarcas() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una marca");

        // Array de marcas de coche (puedes personalizarlo con tus marcas específicas)
        final String[] marcas = {"Toyota", "Honda", "Ford", "Chevrolet"};

        builder.setItems(marcas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String marcaSeleccionada = marcas[which];
                filtrarPorMarca(marcaSeleccionada);
            }
        });

        builder.show();
    }
    private void filtrarPorMarca(String marca) {
        if (marca.equals("Toyota")) {


                Intent i = new Intent(getBaseContext(),PrimeraPantalla.class);
                startActivity(i);

        } else {
            // Aquí puedes manejar el caso en que se selecciona una marca diferente a Toyota, si es necesario
        }
    }
}