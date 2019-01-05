package com.example.luisalex.farmaciaapp;

import com.example.luisalex.farmaciaapp.ComunicacionDB.Rest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luisalex.farmaciaapp.ComunicacionDB.DB;

import java.io.IOException;
import java.net.URL;

public class ActividadUsuario extends AppCompatActivity{
    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario);

        tvUser = findViewById(R.id.tvUser);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvUser.setText(bundle.getString("usuario"));
        }

        // Cuando ya se ha registrado el usuario se conecta con el servicio de la app
        Rest rest = null;
        try {
            rest = new Rest();
            rest.getResource(new URL("http://localhost:8080/DSS-P4/rest/farmacias"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
