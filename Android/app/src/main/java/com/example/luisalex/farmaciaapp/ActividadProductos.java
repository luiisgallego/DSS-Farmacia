package com.example.luisalex.farmaciaapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.luisalex.farmaciaapp.modelo.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActividadProductos extends AppCompatActivity {
    private static final String TAG = "INICIO_FLAG";
    private String url = "http://192.168.0.158:8080/DSS-P4/rest/productos";
    ArrayList<HashMap<String, String>> productos;
    private ProgressDialog progress;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_productos);
        productos = new ArrayList<>();

        lv = (ListView) findViewById(R.id.actividad_productos);

        new HTTPGet().execute();

        setTitle("Productos");

        registerForContextMenu(lv);

        Order pedido = new Order(1,20,16,7,3);
        new HTTPPostPedido(pedido).execute();

    }

    public class HTTPGet extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // It will use pre defined preExecute method in async task
            super.onPreExecute();
            progress = new ProgressDialog(ActividadProductos.this);
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
                    JSONArray f = jObj.getJSONArray("productos");

                    for (int i = 0; i < f.length(); i++) {
                        JSONObject farmacia = f.getJSONObject(i);
                        String nombre = farmacia.getString("nombre");
                        String cantidad = "Cantidad: " + farmacia.getString("cantidad");
                        String precio = "Precio: " + farmacia.getString("precio") + "€";

                        HashMap<String, String> datos = new HashMap<>();
                        datos.put("nombre", nombre);
                        datos.put("cantidad", cantidad);
                        datos.put("precio", precio);

                        productos.add(datos);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("TEMPORAL", "Ha habido un problema al convertir JSON del get.");
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
                    ActividadProductos.this,
                    productos,
                    R.layout.lista_productos,
                    new String[]{"nombre","precio","producto"},
                    new int[]{R.id.list_nombre, R.id.list_cantidad, R.id.list_precio});

            lv.setAdapter(adapter);
        }

    }

    public class HTTPPostPedido extends AsyncTask <Void, Void, Void>{

        private Order pedido;

        public HTTPPostPedido(Order pedido){this.pedido=pedido;}

        @Override
        protected Void doInBackground(Void... voids) {
            REST rest = new REST();
            try {
                rest.postPedido(pedido.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
