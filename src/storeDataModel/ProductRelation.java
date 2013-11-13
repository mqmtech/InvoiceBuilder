package storeDataModel;

import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable (detachable = "true")
@FetchGroup(name = "detach_product", members = {@Persistent(name="producto")})
public class ProductRelation {
	private Product producto;
	
	private int cantidad;
	private float importe;
	
	//Copy to keep a copy of the price and desc in that moment (this could change in the product in the future)
	private float precio;
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private String descripcion;
	
	public ProductRelation(){	
	}
	
	public ProductRelation(Product p_producto, int p_cantidad, float p_importe){
		producto = p_producto;
		cantidad = p_cantidad;
		importe = p_importe;
		
		//Make an snapshot for price (as this can change in the product in future but we need to keep the original price)
		precio = p_producto.getPrecio();
		descripcion = p_producto.getDescripcion();
	}
	
	@Override
	public String toString(){
		return producto + " con cantidad: " + cantidad + " e importe de: " + importe; 
	}
	
	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}
}
