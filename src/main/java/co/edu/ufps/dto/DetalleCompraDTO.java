package co.edu.ufps.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.DetallesCompra;
import co.edu.ufps.entities.Producto;
import co.edu.ufps.entities.TipoDocumento;
import lombok.Data;

@Data
public class DetalleCompraDTO {
    private String referencia;
    private Integer cantidad;
    private BigDecimal descuento;

    public static DetalleCompraDTO fromEntity(DetallesCompra detallesCompra) {
        DetalleCompraDTO dto = new DetalleCompraDTO();
        
        if(detallesCompra.getProducto() != null) {
        	dto.setReferencia(detallesCompra.getProducto().getReferencia());
        }
        
        dto.setCantidad(detallesCompra.getCantidad());
        dto.setDescuento(detallesCompra.getDescuento());
        return dto;
    }
    
    public DetallesCompra toEntity() {
        DetallesCompra detallesCompra = new DetallesCompra();
        
        if(this.referencia != null) {
        	Producto producto = new Producto();
        	producto.setReferencia(referencia);
        	
        	detallesCompra.setProducto(producto);
        }
        
        detallesCompra.setCantidad(this.cantidad);
        detallesCompra.setDescuento(this.descuento);
        return detallesCompra;
    }
    
}
