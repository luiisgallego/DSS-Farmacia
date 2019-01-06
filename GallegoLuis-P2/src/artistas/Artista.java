package artistas;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Artista {
	
	private Integer ID;
	private String nombre;
	private String tipo;
	private Integer edad;
	private String descripcion;
	
	public Artista(){}
	
	public Artista(Integer ID, String nombre, String tipo, Integer edad, String descripcion){
		this.ID = ID;
		this.nombre = nombre;
		this.tipo = tipo;
		this.edad = edad;
		this.descripcion = descripcion;
	}
	
	public Integer getID(){
		return ID;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public Integer getEdad(){
		return edad;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setID(Integer ID){
		this.ID = ID;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public void setEdad(Integer edad){
		this.edad = edad;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}	
}
