package co.edu.ufps.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public Optional<Cliente> findOneByDocumentoAndTipoDocumentoNombre(String documento, String tipoDocumento);
}
