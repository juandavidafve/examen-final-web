package co.edu.ufps.controller;

import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.dto.CompraCrearResDTO;
import co.edu.ufps.dto.FacturaCrearReqDTO;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.entities.Tienda;
import co.edu.ufps.services.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class FacturaController {
	
	@Autowired
	CompraService compraService;
	
	@PostMapping("crear/{uuidTienda}")
	public CompraCrearResDTO crearFactura(@PathVariable String uuidTienda, @RequestBody FacturaCrearReqDTO compraDTO) {
		Compra compra = compraDTO.toEntity();
		Tienda tienda = new Tienda();
		tienda.setUuid(uuidTienda);
		compra.setTienda(tienda);
		
		compraService.crearFactura(compra);
		
		CompraCrearResDTO resDTO = CompraCrearResDTO.fromEntity(compra);
		resDTO.setMessage("La factura se ha creado correctamente con el número: " + compra.getId());
				
		return resDTO;
	}
	
}
