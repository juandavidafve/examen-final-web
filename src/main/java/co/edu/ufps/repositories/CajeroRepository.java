package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Cajero;

public interface CajeroRepository extends JpaRepository<Cajero, Integer> {
	public Optional<Cajero> findOneByToken(String token);
}
