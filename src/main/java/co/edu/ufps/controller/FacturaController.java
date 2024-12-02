package co.edu.ufps.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class FacturaController {
	
	@PostMapping("crear/{uuidTienda}")
	public String crearFactura(@PathVariable String uuidTienda) {
		return "Hello World";
	}
	
}
