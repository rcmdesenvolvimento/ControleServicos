package com.rcm.sistemas.clientes.domain.model.repository;

import com.rcm.sistemas.clientes.domain.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
