package servidor;

public class Usuario {
	
	private String nick;
	private String nombre;
	private int edad;
	private String email;
	private String pass;
	
	public Usuario () {
		this.nick = "#";
		this.nombre = "#";
		this.edad = 0;
		this.email = "#";
		this.pass = "#";
	}
	
	public Usuario (String nick, String nombre, int edad, String email, String pass) {
		this.nick = nick;
		this.nombre = nombre;
		this.edad = edad;
		this.email = email;
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
//	@Override
//	public String toString() {
//		String salida = "NICK = " + this.nick +
//						" NOMBRE = " + this.nombre +
//						" EDAD = " + this.edad +
//						" EMAIL = " + this.email +
//						" PASSWORD = " + this.pass; 
//		return salida;
//				
//	}
}
