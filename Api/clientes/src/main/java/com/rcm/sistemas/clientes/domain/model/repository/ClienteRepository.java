package com.rcm.sistemas.clientes.domain.model.repository;

import com.rcm.sistemas.clientes.domain.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
