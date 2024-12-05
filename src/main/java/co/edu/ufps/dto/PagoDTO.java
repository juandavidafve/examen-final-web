package co.edu.ufps.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.ufps.entities.Pago;
import co.edu.ufps.entities.TipoPago;
import lombok.Data;

@Data
public class PagoDTO {
	@JsonProperty("tipo_pago")
    private String tipoPago;
	
	@JsonProperty("tipo_tarjeta")
    private String tipoTarjeta;
    private Integer cuotas;
    private Integer valor;

    public static PagoDTO fromEntity(Pago pago) {
        PagoDTO dto = new PagoDTO();
        if (pago.getTipoPago() != null) {
            dto.setTipoPago(pago.getTipoPago().getNombre());
        }
        
        dto.setTipoTarjeta(pago.getTarjetaTipo());
        
        dto.setCuotas(pago.getCuotas());
        dto.setValor(pago.getValor());
        return dto;
    }

    public Pago toEntity() {
        Pago pago = new Pago();
        if (this.tipoPago != null) {
            TipoPago tipoPago = new TipoPago();
            tipoPago.setNombre(this.tipoPago);
            pago.setTipoPago(tipoPago);
        }
        
        if(this.valor != null) {
        	 pago.setValor(this.valor);
        }
        
        if(this.cuotas != null) {
        	pago.setCuotas(this.cuotas);
        }
        
        pago.setTarjetaTipo(this.tipoTarjeta);
        return pago;
    }
}