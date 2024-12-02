package co.edu.ufps.dto;

import co.edu.ufps.entities.Compra;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompraCrearResDTO {
	private String status;
	private String message;
	private DataCreateCompraDTO data;
	
	public static CompraCrearResDTO fromEntity(Compra compra) {
		CompraCrearResDTO resDTO = new CompraCrearResDTO();
		resDTO.setData(DataCreateCompraDTO.fromEntity(compra));
		return resDTO;
	}
}
