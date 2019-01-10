package com.example.luisalex.farmaciaapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ActividadFarmacias extends AppCompatActivity{
    private static final String TAG = "INICIO_FLAG";
    private String url = "http://localhost:8080/DSS-P4/rest/farmacias";
    ArrayList<HashMap<String,String>> farmacias;

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

        REST rest = new REST();
        String farmacias = rest.doGet("http://192.168.0.158:8080/DSS-P4/rest/farmacias");
        Log.e(TAG,farmacias+"EJEMPLOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
    }


    public class HTTPGet extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // It will use pre defined preExecute method in async task
            super.onPreExecute();
            //progress = new ProgressDialog(PharmacysActivity.this);
            // Show what you want in the progress dialog
            //progress.setMessage("Loading...");
            // Progress dialog is not cancellable here
            //progress.setCancelable(false);

            //progress.show();
        }


        @Override
        protected String doInBackground(String... urls) {


            REST rest = new REST();
            String cadenaJSON = rest.getRecurso(url);
            Log.e(TAG,"Response from URL: " + cadenaJSON);

            if(cadenaJSON !=null){
                try{
                    JSONObject jObj = new JSONObject(cadenaJSON);
                    // Fetch array from studentsinfo object
                    JSONArray p = jObj.getJSONArray("farmacias");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Log.i(RESTLOG,result);
        }



    }



}
