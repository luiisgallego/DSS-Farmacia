package com.example.luisalex.farmaciaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.luisalex.farmaciaapp.modelo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActividadFarmacias extends AppCompatActivity {
    private static final String TAG = "INICIO_FLAG";
    private String url = "http://192.168.0.158:8080/DSS-P4/rest/farmacias";
    ArrayList<HashMap<String, String>> farmacias;
    private ProgressDialog progress;
    private ListView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_farmacias);
        farmacias = new ArrayList<>();

        tvUser = (ListView) findViewById(R.id.actividad_farmacias);

        new HTTPGet().execute();

        setTitle("Farmacias");

        registerForContextMenu(tvUser);

        // Pulsamos el botón para ver todos los productos
        Button b = (Button)findViewById(R.id.mostrar_productos);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActividadFarmacias.this, ActividadProductos.class));
            }
        });

        // Pulsamos el botón para ver el carrito
        ImageButton b2 = (ImageButton)findViewById(R.id.mostrar_carrito);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActividadFarmacias.this, ActividadCarrito.class));
            }
        });

    }


    public class HTTPGet extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // It will use pre defined preExecute method in async task
            super.onPreExecute();
            progress = new ProgressDialog(ActividadFarmacias.this);
            // Show what you want in the progress dialog
            progress.setMessage("Cargando...");
            // Progress dialog is not cancellable here
            progress.setCancelable(false);

            progress.show();
        }


        @Override
        protected Void doInBackground(Void... urls){


            REST rest = new REST();
            String cadenaJSON = null;
            try {
                cadenaJSON = rest.getRecurso(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "Response from URL: " + cadenaJSON);

            if (cadenaJSON != null) {
                try {
                    JSONObject jObj = new JSONObject(cadenaJSON);
                    JSONArray f = jObj.getJSONArray("farmacias");


                    for (int i = 0; i < f.length(); i++) {
                        JSONObject farmacia = f.getJSONObject(i);
                        String id = farmacia.getString("ID");
                        String nombre = farmacia.getString("nombre");
                        String latitud = "latitud: " + farmacia.getString("latitud");
                        String longitud = "longitud: " + farmacia.getString("longitud");

                        HashMap<String, String> datos = new HashMap<>();
                        datos.put("id", id);
                        datos.put("nombre", nombre);
                        datos.put("latitud", latitud);
                        datos.put("longitud", longitud);

                        farmacias.add(datos);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.e(TAG, "No se ha conseguido el JSON del servidor");
            }
            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (progress.isShowing()) {
                progress.dismiss();
            }

            android.widget.ListAdapter adapter = new SimpleAdapter(
                    ActividadFarmacias.this,
                    farmacias,
                    R.layout.lista_farmacias,
                    new String[]{"nombre", "latitud", "longitud"},
                    new int[]{R.id.list_farmacia, R.id.list_latitud, R.id.list_longitud});

            tvUser.setAdapter(adapter);
        }

    }


}




