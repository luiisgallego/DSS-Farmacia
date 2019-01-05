package com.example.luisalex.farmaciaapp.ComunicacionDB;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



//https://code.tutsplus.com/es/tutorials/android-from-scratch-using-rest-apis--cms-27117
public class Rest {
    private HttpURLConnection conexion;
    private JsonReader jsonReader;

    public Rest() throws IOException {



        URL url = new URL("http://localhost:8080/DSS-P4/rest/farmacias");
        this.conexion = (HttpURLConnection) url.openConnection();
        if (this.conexion.getResponseCode() == 200) {
            InputStream responseBody = conexion.getInputStream();
            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
            this.jsonReader= new JsonReader(responseBodyReader);
        } else {
            // Error handling code goes here
        }

    }
}
