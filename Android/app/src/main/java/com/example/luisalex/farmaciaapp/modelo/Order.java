package com.example.luisalex.farmaciaapp.modelo;

public class Order {
	
	private int ID;
	private int precio;
	private int productoID;
	private int farmaciaID;
	private int usuarioID;
	
	public Order () {
		this.ID = 0;
		this.precio = 0;
		this.usuarioID = 0;
		this.productoID = 0; 
		this.farmaciaID = 0; 
	}
	
	public Order (int ID, int precio, int usuarioID, int productoID, int farmaciaID) {
		this.ID = ID;
		this.precio = precio;
		this.usuarioID = usuarioID;
		this.productoID = productoID;
		this.farmaciaID = farmaciaID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getProductoID() {
		return productoID;
	}

	public void setProductoID(int productoID) {
		this.productoID = productoID;
	}

	public int getFarmaciaID() {
		return farmaciaID;
	}

	public void setFarmaciaID(int farmaciaID) {
		this.farmaciaID = farmaciaID;
	}

	public int getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}
}
