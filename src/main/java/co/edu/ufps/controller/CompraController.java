package co.edu.ufps.controller;

import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.dto.CompraCrearResDTO;
import co.edu.ufps.dto.CompraConsultaReqDTO;
import co.edu.ufps.dto.CompraConsultaResDTO;
import co.edu.ufps.dto.CompraCrearReqDTO;
import co.edu.ufps.entities.Cajero;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.entities.Tienda;
import co.edu.ufps.exceptions.BusinessException;
import co.edu.ufps.exceptions.ResourceNotFoundException;
import co.edu.ufps.services.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class CompraController {
	
	@Autowired
	CompraService compraService;
	
	@PostMapping("crear/{uuidTienda}")
	public ResponseEntity<CompraCrearResDTO> crearFactura(@PathVariable String uuidTienda, @RequestBody CompraCrearReqDTO compraDTO) {
		Compra compra = compraDTO.toEntity();
		Tienda tienda = new Tienda();
		tienda.setUuid(uuidTienda);
		compra.setTienda(tienda);
		
		try {
			compra = compraService.crearFactura(compra);
			CompraCrearResDTO resDTO = CompraCrearResDTO.fromEntity(compra);
			resDTO.setMessage("La factura se ha creado correctamente con el número: " + compra.getId());
			resDTO.setStatus("success");
			return ResponseEntity.ok(resDTO);	
		} catch(ResourceNotFoundException e) {
			CompraCrearResDTO resDTO = new CompraCrearResDTO();
			resDTO.setStatus("error");
			resDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resDTO);
		} catch(BusinessException e) {
			CompraCrearResDTO resDTO = new CompraCrearResDTO();
			resDTO.setStatus("error");
			resDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resDTO);
		}
	}
	
	@PostMapping("consultar/{uuidTienda}")
	public ResponseEntity<?> consultarFactura(@PathVariable String uuidTienda, @RequestBody CompraConsultaReqDTO dto) {
		Compra compra = dto.getFactura();
		Cajero cajero = dto.getCajero();
		Cliente cliente = dto.getCliente();
		Tienda tienda = new Tienda();
		tienda.setUuid(uuidTienda);
		compra.setTienda(tienda);
		
		try {
			compra = compraService.consultarFactura(compra, cajero, cliente);
			CompraConsultaResDTO resDTO = CompraConsultaResDTO.fromEntity(compra);
			return ResponseEntity.ok(resDTO);	
		} catch(ResourceNotFoundException e) {
			CompraCrearResDTO resDTO = new CompraCrearResDTO();
			resDTO.setStatus("error");
			resDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resDTO);
		} catch(BusinessException e) {
			CompraCrearResDTO resDTO = new CompraCrearResDTO();
			resDTO.setStatus("error");
			resDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resDTO);
		}
	}
}
