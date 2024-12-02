package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}
