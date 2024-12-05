package co.edu.ufps.dto;

import co.edu.ufps.entities.Cajero;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Compra;
import lombok.Data;

@Data
public class CompraConsultaReqDTO {
	private String token;
	private String cliente;
	private Integer factura;
	
	public Compra getFactura() {
		Compra compra = new Compra();
		compra.setId(this.factura);
		return compra;
	}
	
	public Cliente getCliente() {
		Cliente cliente = new Cliente();
		cliente.setDocumento(this.cliente);
		return cliente;
	}
	
	public Cajero getCajero() {
		Cajero cajero = new Cajero();
		cajero.setToken(this.token);
		return cajero;
	}
}
