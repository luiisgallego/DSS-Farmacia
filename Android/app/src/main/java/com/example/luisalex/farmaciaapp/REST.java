package com.example.luisalex.farmaciaapp;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.luisalex.farmaciaapp.modelo.Producto;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



//https://code.tutsplus.com/es/tutorials/android-from-scratch-using-rest-apis--cms-27117
public class REST{
    private static final String RESTLOG = "REST";
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    public REST(){

    }

    public String getRecurso(String myURL) throws IOException {
        //realizamos la conexión para recibir el json
        String salida;
        URL url = new URL(myURL);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        try{
            // Configuramos la conexion
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);
            conexion.setRequestProperty("Content-Type","application/json");
            conexion.setRequestProperty("User-Agent", USER_AGENT);
            conexion.connect();
            int response = conexion.getResponseCode();
            Log.e(RESTLOG,"Código de respuesta: " + response);
            if(response == 200) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                if(in != null)
                    salida = convertInputStreamToString(in);
                else
                    salida = "Did not work!";
            }
            else{
                salida = "Error en la llamada GET: Código: " + response;
            }
        } finally{
            conexion.disconnect();
        }

        return salida;

    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private static void postRecurso(Producto producto) throws IOException {
        URL url = new URL("http://localhost:8080/DSS-P4/rest/productos");
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
            salida= "Error al conectar con recurso.";
        }
    }

}
