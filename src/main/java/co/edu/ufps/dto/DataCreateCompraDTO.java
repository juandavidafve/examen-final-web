package co.edu.ufps.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.ufps.entities.Compra;
import co.edu.ufps.entities.DetallesCompra;
import co.edu.ufps.entities.Pago;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataCreateCompraDTO {
	private Integer numero;
	private BigDecimal total;
	private LocalDate fecha;
	
	public static DataCreateCompraDTO fromEntity(Compra compra) {
		DataCreateCompraDTO dataDTO = new DataCreateCompraDTO();
		dataDTO.setFecha(LocalDate.now());
		dataDTO.setTotal(compra.getTotal());
		dataDTO.setNumero(compra.getId());
		
		return dataDTO;
	}
}
