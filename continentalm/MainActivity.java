package com.example.continentalm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText edtUsuario,edtpassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario=findViewById(R.id.edtUsuario);
        edtpassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    validarUsuario("http://192.168.100.48:80/mipropiajefa/validar_usuario.php");

            }


        });
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.toString() .equals("1")){

                    Intent intent=new Intent(getApplicationContext(), princi2.class);
                    startActivity(intent);
                }else{
                    verificarCampos();
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("usuario",edtUsuario.getText().toString());
                parametros.put("password",edtpassword.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private boolean verificarCampos() {
        EditText editTextUsuario = findViewById(R.id.edtUsuario);
        EditText editTextContraseña = findViewById(R.id.edtPassword);

        String usuario = editTextUsuario.getText().toString().trim();
        String contrasena = editTextContraseña.getText().toString().trim();
        boolean resp=true;
        if (usuario.isEmpty()) {
            // Mostrar alerta
            Toast.makeText(getApplicationContext(),"Usuario requerido",Toast.LENGTH_SHORT).show();
            resp=false;
        } else if(contrasena.isEmpty()){
            Toast.makeText(getApplicationContext(),"Contraseña requerida",Toast.LENGTH_SHORT).show();
            resp=false;
        }
        return resp;
    }








}