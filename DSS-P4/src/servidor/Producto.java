package servidor;

public class Producto {
	
	private int ID;
	private String nombre;
	private String descripcion;
	
	public Producto() {
		this.ID = 0;
		this.nombre = "#";
		this.descripcion = "#";
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Producto(int ID, String nombre, String descripcion) {
		this.ID = ID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
//	@Override
//	public String toString() {
//		String salida = "ID = " + this.ID +
//						" NOMBRE = " + this.nombre +
//						" DESCRIPCIÓN = " + this.descripcion;
//		return salida;
//				
//	}
		
}
