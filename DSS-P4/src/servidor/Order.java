package servidor;

public class Order {
	
	private int ID;
	private int precio;
	private Usuario usuario;
	private Producto producto;
	private Farmacia farmacia;
	
	public Order () {
		this.ID = 0;
		this.precio = 0;
		this.usuario = new Usuario();
		this.producto = new Producto();
		this.farmacia = new Farmacia();
	}
	
	public Order (int ID, int precio, Usuario usuario, Producto producto, Farmacia farmacia) {
		this.ID = ID;
		this.precio = precio;
		this.usuario = usuario;
		this.producto = producto;
		this.farmacia = farmacia;
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
	
	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
//	@Override
//	public String toString() {
//		String salida = "ID = " + this.ID +
//						" PRECIO = " + this.precio +
//						" USUARIO = " + this.usuario.getNombre() +
//						" PRODUCTO = " + this.producto.getNombre() + 
//						" FARMACIA = " + this.farmacia.getNombre();
//		return salida;
//				
//	}
}
