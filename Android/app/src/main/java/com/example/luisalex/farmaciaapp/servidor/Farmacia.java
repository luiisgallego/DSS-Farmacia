package servidor;

public class Farmacia {
	
	private int ID;
	private String nombre;
	private float latitud;
	private float longitud;
	
	public Farmacia() {
		this.ID = 0;
		this.nombre = null;
		this.latitud = 0;
		this.longitud = 0;
	}
	
	public Farmacia(int ID, String nombre, float latitud, float longitud) {		
		this.ID = ID;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;		
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

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
}


