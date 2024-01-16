package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long>
{
}
