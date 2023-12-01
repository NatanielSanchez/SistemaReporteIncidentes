package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long>
{
    Optional<TipoCliente> findByTipo(String tipo);
}
