package com.example.luisalex.farmaciaapp.modelo;

import com.example.luisalex.farmaciaapp.modelo.Producto;

import java.util.HashMap;

public class Carrito {

    private HashMap<String,Producto> productos;
    private int importe;

    public Carrito(){
        productos = new HashMap<>();
        importe = 0;
    }

    public void addProducto(Producto p){
        if(!productos.containsKey(String.valueOf(p.getID()))){
            importe += p.getPrecio() * p.getCantidad();
            productos.put(String.valueOf(p.getID()),p);
        }
    }

    public void removeProducto(Producto p){
        if(productos.containsKey(String.valueOf(p.getID()))){
            importe -= p.getPrecio() * p.getCantidad();
            productos.remove(String.valueOf(p.getID()));
        }
    }

    public HashMap<String, Producto> getProductos() {
        return productos;
    }

    public float getImporte() {
        return importe;
    }

    public void setProductos(HashMap<String, Producto> productos) {
        this.productos = productos;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }


}


