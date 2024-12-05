package co.edu.ufps.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.DetallesCompra;
import co.edu.ufps.entities.Producto;
import co.edu.ufps.entities.TipoDocumento;
import lombok.Data;

@Data
public class DetalleCompraResDTO {
    private String referencia;
    private String nombre;
    private Integer cantidad;
    private Integer precio;
    private Integer descuento;
    private Integer subtotal;


    public static DetalleCompraResDTO fromEntity(DetallesCompra detallesCompra) {
        DetalleCompraResDTO dto = new DetalleCompraResDTO();
        
        if(detallesCompra.getProducto() != null) {
        	dto.setReferencia(detallesCompra.getProducto().getReferencia());
        	dto.setNombre(detallesCompra.getProducto().getNombre());
        	dto.setPrecio(detallesCompra.getProducto().getPrecio());
        }
        
        dto.setCantidad(detallesCompra.getCantidad());
        dto.setDescuento(detallesCompra.getDescuento());
        dto.setSubtotal(detallesCompra.getPrecio());
        return dto;
    }
    
    public static List<DetalleCompraResDTO> fromEntity(List<DetallesCompra> detallesCompras){
    	List<DetalleCompraResDTO> dtos = new ArrayList<DetalleCompraResDTO>();
    	
    	for(DetallesCompra detalles : detallesCompras) {
    		dtos.add(DetalleCompraResDTO.fromEntity(detalles));
    	}
    	
    	return dtos;
    }
    
}
