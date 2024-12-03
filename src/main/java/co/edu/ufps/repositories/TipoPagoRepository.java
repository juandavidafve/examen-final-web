package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.TipoPago;

public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
	public Optional<TipoPago> findOneByNombre(String nombre);
}
