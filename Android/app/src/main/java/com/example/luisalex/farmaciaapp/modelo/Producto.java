package com.example.luisalex.farmaciaapp.modelo;

public class Producto {
	
	private int ID;
	private String nombre;
	private int cantidad;
	private int precio;
	private String imagen;
	
	public Producto() {
		this.ID = 0;
		this.nombre = null;
		this.cantidad = 0;
		this.precio = 0;
		this.imagen = null;
	}
	
	public Producto(int ID, String nombre, int cantidad, int precio, String imagen) {
		this.ID = ID;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.imagen = imagen;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}		
}
