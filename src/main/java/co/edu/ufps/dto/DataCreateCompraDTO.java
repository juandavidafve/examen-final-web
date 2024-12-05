package co.edu.ufps.dto;

import co.edu.ufps.entities.Compra;
import lombok.Data;

@Data
public class DataCreateCompraDTO {
	private Integer numero;
	private Integer total;
	private String fecha;
	
	public static DataCreateCompraDTO fromEntity(Compra compra) {
		DataCreateCompraDTO dataDTO = new DataCreateCompraDTO();
		dataDTO.setFecha(compra.getFecha().toLocalDateTime().toLocalDate().toString());
		dataDTO.setTotal(compra.getTotal());
		dataDTO.setNumero(compra.getId());
		
		return dataDTO;
	}
}
