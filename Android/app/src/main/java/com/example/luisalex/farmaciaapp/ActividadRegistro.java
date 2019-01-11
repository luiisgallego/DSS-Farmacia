package com.example.luisalex.farmaciaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisalex.farmaciaapp.modelo.DBHelper;
import com.example.luisalex.farmaciaapp.modelo.REST;
import com.example.luisalex.farmaciaapp.modelo.Usuario;
import com.example.luisalex.farmaciaapp.modelo.UsuarioInicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ActividadRegistro extends AppCompatActivity {
    String sesionID;
    private EditText textNick;
    private EditText textNombre;
    private EditText textEmail;
    private EditText textPass;

    private Button botonRegistrame;

//---------------------------------------------------------------------------------------------------------------------//
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
                    // Hacemos un Get de ese mismo usuario por su nick para averiguar el id que tiene
                    new HTTPGet(textNick.getText().toString()).execute();
                    Log.e("REGISTRO","ID de sesion almacenada: " + sesionID);
                    // Cambiamos de vista
                    Intent intent = new Intent(ActividadRegistro.this, ActividadFarmacias.class);
                    startActivity(intent);
                    // Hacemos un Toast para enseñarselo al usuario
                    Toast.makeText(ActividadRegistro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    // Ponemos todos los campos vacios
                    textNick.setText("");
                    textPass.setText("");
                    textNombre.setText("");
                    textEmail.setText("");
                }else{
                    // Si alguno de los campos se encuentra vacío notificamos con un Toast al usuario.
                    Toast.makeText(ActividadRegistro.this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//---------------------------------------------------------------------------------------------------------------------//

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(textNick.getText().toString()) || TextUtils.isEmpty(textPass.getText().toString()) || TextUtils.isEmpty(textEmail.getText().toString()) || TextUtils.isEmpty(textNombre.getText().toString()) ) {
            return true;
        }else {
            return false;
        }
    }

//---------------------------------------------------------------------------------------------------------------------//
    // Para hacer un POST  de un nuevo usuario en el momento de registrarse en el servidor del consorcio.
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

//---------------------------------------------------------------------------------------------------------------------//
    // Para hacer GET de un usuario concreto a traves de su nick
    public class HTTPGet extends AsyncTask<Void, Void, Void> {

        private String nick;

        public HTTPGet(String nick){this.nick=nick;}

        @Override
        protected Void doInBackground(Void... urls){

            REST rest = new REST();
            String cadenaJSON = null;
            try {
                cadenaJSON = rest.getRecurso("http://192.168.0.158:8080/DSS-P4/rest/usuarios/getMovil/" + nick);
                Log.e("URL GET", "http://192.168.0.158:8080/DSS-P4/rest/usuarios/getMovil/" + nick);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("USUARIO", "Response from URL: " + cadenaJSON);

            if (cadenaJSON != null) {
                try {
                    JSONObject jObj = new JSONObject(cadenaJSON);
                    JSONObject usuario= jObj.getJSONObject("usuario");
                    String id = usuario.getString("ID");
                    sesionID=id;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.e("USUARIO", "No se ha conseguido el JSON del servidor");
            }
            return null;
        }

    }

//---------------------------------------------------------------------------------------------------------------------//
}
