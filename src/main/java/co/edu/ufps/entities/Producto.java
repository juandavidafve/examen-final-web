package co.edu.ufps.entities;

import java.math.BigDecimal;

import co.edu.ufps.exceptions.BusinessException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    @ManyToOne
    @JoinColumn(name = "tipo_producto_id")
    private TipoProducto tipoProducto;
    private Integer cantidad;
    private String referencia;
    
    public void consumir(Integer cantidad) {
    	if(cantidad > this.cantidad) {
    		throw new BusinessException("La cantidad a comprar supera el máximo del producto en tienda");
    	}
    	
    	this.cantidad -= cantidad;
    }
}