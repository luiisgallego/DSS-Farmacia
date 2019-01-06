package artistas;

import java.util.HashMap;
import java.util.Map;

public enum ArtistaDao {
	INSTANCE;	// Necesario para el patron Singleton
	
	private Map<Integer, Artista> proveedorContenidos = new HashMap<>();
	
	private ArtistaDao(){
		// Creamos contenido inicial
		
		Artista artista = new Artista(1, "Richie Hawtin", "Musica", 45, "Uno de los mayores referentes en musica electronica");
		proveedorContenidos.put(artista.getID(), artista);		
		artista = new Artista(2, "Blawan", "Musica", 31, "Techno en su mayor exponente");
		proveedorContenidos.put(artista.getID(), artista);	
		artista = new Artista(3, "Banksy", "Arte callejero", 1, "Arte urbano como critica");
		proveedorContenidos.put(artista.getID(), artista);	
		artista = new Artista(4, "Nina Kraviz", "Musica", 35, "Reina del Acid");
		proveedorContenidos.put(artista.getID(), artista);			
	}
	
	public Map<Integer, Artista> getModel(){
		return proveedorContenidos;
	}
}
