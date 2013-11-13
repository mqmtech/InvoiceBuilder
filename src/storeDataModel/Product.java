package storeDataModel;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable (detachable = "true")
public class Product {
	
	@PrimaryKey
	private String codigo;
	private String descripcion=null;
	private float precio = 0.00f;
	

	public Product(String _codigo){
		codigo = _codigo;
	}	
	
	public Product(String _codigo, String _descripcion, float _precio){
		codigo = _codigo;
		descripcion = _descripcion;
		precio = _precio;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString(){
		return "Producto "+ codigo;
	}
}
