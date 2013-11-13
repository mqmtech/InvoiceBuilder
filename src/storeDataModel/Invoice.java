package storeDataModel;

import java.util.ArrayList;
import java.util.Date;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**Detachable para usar copias del objeto persistente fuera de una transaccion y al
 * hacer de nuevo pesistente se actualice el antiguo objeto.
 * 
 *  Si no hacemos Detachable entonces una vez hecho persistente solo se puede modificar el
 *  objeto dentro de una transaccion (al guardar o recuperar la instancia de la base d datos) *
 */
@PersistenceCapable (detachable = "true")
@FetchGroup(name = "detach_productsRelation", members = {@Persistent(name="productsRelation")})
public class Invoice {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	
	private Date fecha = new Date();
	
	private String matricula = "sin matricula";
	private String modelo = "sin modelo";
	
	private float baseImponible = 0.00f;
	private float iva = 0.00f;
	private float importeIva = 0.00f;
	private float total = 0.00f;
	
	@Join
	private ArrayList<ProductRelation> productsRelation=new ArrayList<ProductRelation>();

	public Invoice(String _matricula){
		matricula = _matricula;
	}
	
	public Invoice (String _matricula, String _modelo, float _baseImponible, float _iva, float _importeIva, float _total){
		matricula = _matricula;
		modelo = _modelo;
		baseImponible = _baseImponible;
		iva = _iva;
		importeIva = _importeIva;
		total = _total;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(float baseImponible) {
		this.baseImponible = baseImponible;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public float getImporteIva() {
		return importeIva;
	}

	public void setImporteIva(float importeIva) {
		this.importeIva = importeIva;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	
    public void addProductRelation(ProductRelation rel)
    {
    	productsRelation.add(rel);
    }

    public void removeProductRelation(ProductRelation rel)
    {
    	productsRelation.remove(rel);
    }
    
    public int getNumberOfRelations()
    {
        return productsRelation.size();
    }

	
	public ArrayList<ProductRelation> getProductRelations() {
		return productsRelation;
	}

	public void setProductRelations(ArrayList<ProductRelation> p_productRelations) {
		this.productsRelation = p_productRelations;
	}
	
	@Override
	public String toString(){
		return "Factura con id: " + id + " y matricula: " + matricula; 
	}
}


/**
 * 
 * @author CiberXtrem
 * Class ProductRelation to add attributes to the relation between Factura and Producto 
 *
 */
/*
@PersistenceCapable
class ProductRelation {

	private Product producto;
	private int cantidad;
	private int importe;
	
	public ProductRelation(){	
	}
	
	public ProductRelation(Product p_producto, int p_cantidad, int p_importe){
		producto = p_producto;
		cantidad = p_cantidad;
		importe = p_importe;
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

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}	
}*/

