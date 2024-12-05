package co.edu.ufps.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "detalles_compra")
@Data
public class DetallesCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private Integer cantidad = 0;
    private Integer precio = 0;
    private Integer descuento = 0;
    
    private void updatePrecio() {
    	if(this.producto != null && this.producto.getPrecio() != null) {
    		this.precio = this.cantidad * this.producto.getPrecio() * (100 - this.descuento) / 100;
    	}
    }
    
    public void setProducto(Producto producto) {
    	this.producto = producto;
    	updatePrecio();
    }
    
    public void setCantidad(Integer cantidad) {
    	this.cantidad = cantidad;
    	updatePrecio();
    }
    
    public void setDescuento(Integer descuento) {
    	this.descuento = descuento;
    	updatePrecio();
    }
}