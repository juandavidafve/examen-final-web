package co.edu.ufps.entities;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "cajero_id")
    private Cajero cajero;
    private Integer total = 0;
    private Integer impuestos = 0;
    private Timestamp fecha = new Timestamp(System.currentTimeMillis());
    private String observaciones;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<DetallesCompra> detallesCompra;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Pago> pagos;
    
    public void setDetalleCompra(List<DetallesCompra> detallesCompra) {
    	this.detallesCompra = detallesCompra;
    	this.total = 0;
    	
    	for(DetallesCompra detalleCompra: this.detallesCompra) {
    		if(detalleCompra != null) {
    			this.total += detalleCompra.getPrecio();
    		}
    	}
    	this.total *= (100 + this.impuestos);
    	this.total /= 100;
    }
    
    public Integer getTotalPagos() {
    	Integer totalPagos = 0;
    	for(Pago pago: this.pagos) {
    		if(pago != null) {
    			totalPagos += pago.getValor();
    		}
    	}
    	
    	return totalPagos;
    }
}