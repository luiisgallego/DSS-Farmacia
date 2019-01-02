package servidor;

public class Order {
	
	private int ID;
	private Usuario usuario;
	private Producto producto;
	
	public Order () {
		this.ID = 0;
		this.usuario = new Usuario();
		this.producto = new Producto();
	}
	
	public Order (int ID, Usuario usuario, Producto producto) {
		this.ID = ID;
		this.usuario = usuario;
		this.producto = producto;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
//	@Override
//	public String toString() {
//		String salida = "ID = " + this.ID +
//						" Usuario = " + this.usuario.getNombre() +
//						" Producto = " + this.producto.getNombre() +
//						" LONGITUD = " + this.longitud; 
//		return salida;
//				
//	}
}
