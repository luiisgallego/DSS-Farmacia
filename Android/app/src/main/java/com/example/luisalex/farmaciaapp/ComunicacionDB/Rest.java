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

    public Rest() throws IOException {

    }

    public String getResource(URL url) throws IOException {
        //realizamos la conexión para recibir el json
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        String salida;
        // Comprobamos que el recurso accedido es correcto
        if (conexion.getResponseCode() == 200) {
            InputStream responseBody = conexion.getInputStream();
            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader= new JsonReader(responseBodyReader);
            salida = jsonReader.toString();
            jsonReader.close();
            conexion.disconnect();
        } else {
            salida= "Error al conecatar con recurso.";
        }

        return salida;
    }

    public void postRecurso(URL url,) throws IOException {
        //realizamos la conexión para recibir el json
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        String salida;
        // Comprobamos que el recurso accedido es correcto
        if (conexion.getResponseCode() == 200) {
            InputStream responseBody = conexion.getInputStream();
            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader= new JsonReader(responseBodyReader);
            salida = jsonReader.toString();
            jsonReader.close();
            conexion.disconnect();
        } else {
            salida= "Error al conecatar con recurso.";
        }
    }
}
