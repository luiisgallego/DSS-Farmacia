package com.example.luisalex.farmaciaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.luisalex.farmaciaapp.modelo.Carrito;
import com.example.luisalex.farmaciaapp.modelo.DBHelper;
import com.example.luisalex.farmaciaapp.modelo.Order;
import com.example.luisalex.farmaciaapp.modelo.Producto;
import com.example.luisalex.farmaciaapp.modelo.REST;

import java.util.ArrayList;
import java.util.HashMap;

public class ActividadCarrito extends AppCompatActivity {

    ArrayList<HashMap<String, String>> productos;
    private ProgressDialog progress;
    private ListView lv;
    Carrito carrito;
    final DBHelper dbHelper= new DBHelper(this,"Base Datos",null,1);

//---------------------------------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_carrito);
        carrito = new Carrito();
        productos=new ArrayList<>();

        lv = (ListView) findViewById(R.id.actividad_carrito);

        setTitle("Carrito");

        // Almacenamos la vista del lv de la base de datos interna
        cargarProductos();

        // Pulsamos el botón para confirmar compra
        Button b = (Button) findViewById(R.id.confirmar_compra);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enviamos los pedidos a traves de diferentes POST
                enviarPedidos();
                // Eliminamos los productos del carrito y de la base de datos interna del móvil
                dbHelper.removeProductos();
                carrito = new Carrito();
                // Nos vamos a la pantalla principal
                startActivity(new Intent(ActividadCarrito.this, ActividadFarmacias.class));

            }
        });

    }

//---------------------------------------------------------------------------------------------------------------------//

    public void cargarProductos(){
        // Nos declaramos el vector de productos , los cuales los sacamos de SQLite
        ArrayList<Producto> items = dbHelper.obtenerProductos();
        // Recorremos esos productos
        for (int i = 0; i < items.size(); i++) {
            Producto p = items.get(i);
            // Prepasamos sus campos
            String id = String.valueOf(p.getID());
            String nombre = p.getNombre();
            String cantidad = String.valueOf(p.getCantidad());
            String precio = String.valueOf(p.getPrecio());
            // Ponemos sus datos
            HashMap<String, String> datos = new HashMap<>();
            datos.put("ID", id);
            datos.put("nombre", nombre);
            datos.put("cantidad", cantidad);
            datos.put("precio", precio);

            // Almacenamos
            carrito.addProducto(p);
            productos.add(datos);

            // con "productos" ya podemos montar el listView
            android.widget.ListAdapter adapter = new SimpleAdapter(
                    ActividadCarrito.this,
                    productos,
                    R.layout.lista_productos,
                    new String[]{"ID","nombre","cantidad","precio"},
                    new int[]{R.id.list_id,R.id.list_nombre, R.id.list_cantidad, R.id.list_precio});

            // Lo establecemos
            lv.setAdapter(adapter);

        }

        registerForContextMenu(lv);
    }

//---------------------------------------------------------------------------------------------------------------------//

    public void enviarPedidos(){
        // sacamos los productos del carrito
        HashMap<String,Producto> items = carrito.getProductos();
        // Sacamos sus valores y realizamos un post de ellos a la url adecuada.
        for (HashMap.Entry<String, Producto> entry : items.entrySet()) {
            Producto p = entry.getValue();
            int precio = p.getPrecio();
            int productoID=p.getID();
            Order pedido = new Order(1,precio,7,productoID,3);
            //POST pedido
            new HTTPPostPedido(pedido).execute();
        }

    }

//---------------------------------------------------------------------------------------------------------------------//

    //Para hacer un POST de un pedido
    public class HTTPPostPedido extends AsyncTask<Void, Void, Void> {

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

//---------------------------------------------------------------------------------------------------------------------//


}
