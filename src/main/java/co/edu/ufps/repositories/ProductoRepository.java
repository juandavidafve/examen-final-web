package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	public Optional<Producto> findOneByReferencia(String referencia);
}
