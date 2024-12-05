package co.edu.ufps.dto;

import java.util.List;

import co.edu.ufps.entities.Compra;
import lombok.Data;

@Data
public class CompraConsultaResDTO {
	private Integer total;
	private Integer impuestos;
	private ClienteDTO cliente;
	private List<DetalleCompraResDTO> productos;
	private CajeroResDTO cajero;
	
	public static CompraConsultaResDTO fromEntity(Compra compra) {
		CompraConsultaResDTO dto = new CompraConsultaResDTO();
		
		dto.setTotal(compra.getTotal());
		dto.setImpuestos(compra.getImpuestos());
		
		if(compra.getCliente() != null) {
			dto.setCliente(ClienteDTO.fromEntity(compra.getCliente()));
		}
		
		if(compra.getDetallesCompra() != null) {
			dto.setProductos(DetalleCompraResDTO.fromEntity(compra.getDetallesCompra()));
		}
		
		if(compra.getCajero() != null) {
			dto.setCajero(CajeroResDTO.fromEntity(compra.getCajero()));
		}
		
		return dto;
	}
}
