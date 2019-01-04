package servidor;

public class Usuario {
	
	private int ID;
	private String nick;
	private String nombre;
	private String rol;
	private String email;
	private String pass;
	
	public Usuario () {
		this.ID = 0;
		this.nick = null;
		this.nombre = null;
		this.rol = null;
		this.email = null;
		this.pass = null;
	}
	
	public Usuario (int ID, String nick, String nombre, String rol, String email, String pass) {
		this.ID = ID;
		this.nick = nick;
		this.nombre = nombre;
		this.rol = rol;
		this.email = email;
		this.pass = pass;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
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
}
