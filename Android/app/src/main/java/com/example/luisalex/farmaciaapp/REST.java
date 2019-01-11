package com.example.luisalex.farmaciaapp;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.luisalex.farmaciaapp.modelo.Producto;
import com.example.luisalex.farmaciaapp.modelo.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;


//https://code.tutsplus.com/es/tutorials/android-from-scratch-using-rest-apis--cms-27117
public class REST{
    private static final String RESTLOG = "REST";
    private static final String USER_AGENT = "Mozilla/5.0";

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

    public void postUsuario(String stringUsuario) throws Exception {

        URL url = new URL("http://192.168.0.158:8080/DSS-P4/rest/usuarios/registro");
        //realizamos la conexión para recibir el json
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setConnectTimeout(15000 /* milliseconds */);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("User-Agent", USER_AGENT);
        //conexion.setRequestProperty("Content-Type", "application/json");

        // For POST only - START
        conexion.setDoOutput(true);
        OutputStream os = conexion.getOutputStream();
        os.write(stringUsuario.getBytes());
        os.flush();
        os.close();

        // Comprobamos que el recurso accedido es correcto
        if (conexion.getResponseCode() == 200) {
            Log.e(RESTLOG,"POST de usuario realizado con éxito.");
        } else {
            Log.e(RESTLOG,"POST de usuario no ha finalizado correctamente. Código: " + conexion.getResponseCode());
        }
    }

    public void postPedido(String stringOrder) throws Exception {

        URL url = new URL("http://192.168.0.158:8080/DSS-P4/rest/pedidos");
        //realizamos la conexión para recibir el json
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setConnectTimeout(15000 /* milliseconds */);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("User-Agent", USER_AGENT);
        //conexion.setRequestProperty("Content-Type", "application/json");

        // For POST only - START
        conexion.setDoOutput(true);
        OutputStream os = conexion.getOutputStream();
        os.write(stringOrder.getBytes());
        os.flush();
        os.close();

        // Comprobamos que el recurso accedido es correcto
        if (conexion.getResponseCode() == 200) {
            Log.e(RESTLOG,"POST de pedido realizado con éxito.");
        } else {
            Log.e(RESTLOG,"POST de pedido no ha finalizado correctamente. Código: " + conexion.getResponseCode());
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
