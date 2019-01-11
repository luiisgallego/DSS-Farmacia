package com.example.luisalex.farmaciaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisalex.farmaciaapp.modelo.Usuario;

public class ActividadRegistro extends AppCompatActivity {
    private EditText textNick;
    private EditText textNombre;
    private EditText textEmail;
    private EditText textPass;

    private Button botonRegistrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro);

        textNick = findViewById(R.id.nickinput);
        textNombre = findViewById(R.id.nombreinput);
        textEmail = findViewById(R.id.emailinput);
        textPass = findViewById(R.id.passinput);

        botonRegistrame = findViewById(R.id.confirmar_registro);

        final DBHelper dbHelper = new DBHelper(this, "Base Datos", null, 1);

        botonRegistrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    // Añadimos a la base de datos pequeña
                    dbHelper.addUsuario(new UsuarioInicio(textNick.getText().toString(), textPass.getText().toString()));
                    // Añadimos a la base MySQL con POST de usuario
                    Usuario usuario = new Usuario(1,textNick.getText().toString(),textNombre.getText().toString(),"Cliente",textEmail.getText().toString(),textPass.getText().toString());
                    new HTTPPostUsuario(usuario).execute();
                    // Hacemos un Toast para enseñarselo al usuario
                    Toast.makeText(ActividadRegistro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    // Ponemos todos los campos vacios
                    textNick.setText("");
                    textPass.setText("");
                    textNombre.setText("");
                    textEmail.setText("");
                    // Entramos en la aplicación
                    startActivity(new Intent(ActividadRegistro.this, ActividadFarmacias.class));
                }else{
                    // Si alguno de los campos se encuentra vacío notificamos con un Toast al usuario.
                    Toast.makeText(ActividadRegistro.this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(textNick.getText().toString()) || TextUtils.isEmpty(textPass.getText().toString()) || TextUtils.isEmpty(textEmail.getText().toString()) || TextUtils.isEmpty(textNombre.getText().toString()) ) {
            return true;
        }else {
            return false;
        }
    }

    public class HTTPPostUsuario extends AsyncTask<Void, Void, Void> {
        private Usuario usuario;
        public HTTPPostUsuario(Usuario usuario){
            this.usuario=usuario;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            REST rest = new REST();
            try {
                rest.postUsuario(usuario.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
