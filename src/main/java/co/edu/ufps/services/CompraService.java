package co.edu.ufps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Compra;
import co.edu.ufps.repositories.CompraRepository;

@Service
public class CompraService {
	@Autowired
	CompraRepository compraRepository;
	
	public Compra crearFactura(Compra compra) {
		return compraRepository.save(compra);
	}
}
