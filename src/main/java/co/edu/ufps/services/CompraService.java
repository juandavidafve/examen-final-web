package co.edu.ufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.exceptions.ResourceNotFoundException;
import co.edu.ufps.repositories.CajeroRepository;
import co.edu.ufps.repositories.ClienteRepository;
import co.edu.ufps.repositories.CompraRepository;

@Service
public class CompraService {
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CajeroRepository cajeroRepository;
	
	public Compra crearFactura(Compra compra) {
		if(compra.getCajero() == null) {
			throw new ResourceNotFoundException("No hay información del cajero.");
		}
		
		if(compra.getVendedor() == null) {
			throw new ResourceNotFoundException("No hay información del vendedor.");
		}
		
		if(compra.getCliente() == null) {
			throw new ResourceNotFoundException("No hay información del cliente.");
		}
		
		return compraRepository.save(compra);
	}
}
