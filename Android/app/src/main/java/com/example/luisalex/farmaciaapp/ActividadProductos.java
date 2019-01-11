package com.example.luisalex.farmaciaapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.luisalex.farmaciaapp.modelo.DBHelper;
import com.example.luisalex.farmaciaapp.modelo.Producto;
import com.example.luisalex.farmaciaapp.modelo.REST;

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
    HashMap<String,Producto> conjuntoProductos;

//---------------------------------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_productos);
        productos = new ArrayList<>();

        final DBHelper dbHelper = new DBHelper(this,"Base Datos",null,1);

        conjuntoProductos = new HashMap<>();

        lv = (ListView) findViewById(R.id.actividad_productos);

        new HTTPGet().execute();

        setTitle("Productos");

        registerForContextMenu(lv);

        // Si seleccionamos uno de los productos de la lista
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // Cogemos los datos que hay en el item
                HashMap<String,String> selectedFromList = (HashMap<String,String>) lv.getItemAtPosition(position);
                // Nos quedamos con el ID
                String idSelec = selectedFromList.get("ID");
                // Buscamos el producto entre los almacenados
                Producto p = conjuntoProductos.get(idSelec);
                // Incluimos ese producto en la base de datos interna SQLite
                dbHelper.addProducto(p);
                // Creamos un toast indicando el proceso
                Toast.makeText(ActividadProductos.this, "Producto " + idSelec + " añadido al carrito.", Toast.LENGTH_SHORT).show();

                Log.i("Click", "click en el elemento " + position + " de mi ListView");
                Log.i("Click", "datos obtenidos: " + idSelec);


            }
        });

    }

//---------------------------------------------------------------------------------------------------------------------//
    // Para hacer un GET al servidor de consorcio con los productos disponibles
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
                        JSONObject producto = f.getJSONObject(i);
                        String id = producto.getString("ID");
                        String nombre = producto.getString("nombre");
                        String cantidad = "Cantidad: " + producto.getString("cantidad");
                        String precio =  "Precio: " + producto.getString("cantidad") + "€";

                        HashMap<String, String> datos = new HashMap<>();
                        datos.put("ID", id);
                        datos.put("nombre", nombre);
                        datos.put("cantidad", cantidad);
                        datos.put("precio", precio);

                        productos.add(datos);
                        Producto p = new Producto(Integer.parseInt(id),nombre,Integer.parseInt(producto.getString("cantidad")),Integer.parseInt(producto.getString("cantidad")),null);
                        conjuntoProductos.put(id,p);

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
                    new String[]{"ID","nombre","cantidad","precio"},
                    new int[]{R.id.list_id,R.id.list_nombre, R.id.list_cantidad, R.id.list_precio});

            lv.setAdapter(adapter);
        }

    }

//---------------------------------------------------------------------------------------------------------------------//

}
