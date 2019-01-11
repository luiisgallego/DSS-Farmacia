package com.example.luisalex.farmaciaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisalex.farmaciaapp.modelo.DBHelper;
import com.example.luisalex.farmaciaapp.modelo.REST;
import com.example.luisalex.farmaciaapp.modelo.UsuarioInicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Main extends AppCompatActivity {

    String sesionID;

    //Texto y botones
    private Button botonLogIn;
    private Button botonSignUp;
    private EditText textNick;
    private EditText textPass;
//---------------------------------------------------------------------------------------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Asociamos cada variable con el elemento de la interfaz
        botonLogIn = findViewById(R.id.botonLogIn);
        botonSignUp = findViewById(R.id.botonSignUp);

        textNick = findViewById(R.id.nickinput);
        textPass = findViewById(R.id.passinput);

        // Cargamos la base de datos local SQLite
        final DBHelper dbHelper = new DBHelper(this,"Base Datos",null,1);

        //Cuando pulsamos en el botón de registrarse
        botonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, ActividadRegistro.class));
            }
        });

        //Cuando pulsamos en el botón de log in
        botonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si los campos no están vacíos
                if (!emptyValidation()) {
                    // Creamos un usuario para la base de datos local
                    UsuarioInicio user = dbHelper.obtenerUsuario(textNick.getText().toString(), textPass.getText().toString());
                    // Si el usuario coincide en nick y pass
                    if (user != null) {
                        // Hacemos un get del nick para averiguar su id en la base de datos del consorcio
                        new HTTPGet(user.getNick()).execute();
                        Log.e("USUARIO","ID de sesion almacenada: " + sesionID);
                        //Pasamos el id de sesion a la siguiente pantalla
                        Intent intent = new Intent(Main.this, ActividadFarmacias.class);
                        startActivity(intent);
                        Toast.makeText(Main.this, "Bienvenido " + user.getNick(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main.this, "Usuario inexistente o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        textPass.setText("");
                    }
                }else{
                    Toast.makeText(Main.this, "Campos vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//---------------------------------------------------------------------------------------------------------------------//

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(textNick.getText().toString()) || TextUtils.isEmpty(textPass.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }

//---------------------------------------------------------------------------------------------------------------------//

    // Clase para hacer Get de un usuario concreto (por su NICK)
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
                    JSONObject usuario= (JSONObject)jObj.get("usuario");
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
