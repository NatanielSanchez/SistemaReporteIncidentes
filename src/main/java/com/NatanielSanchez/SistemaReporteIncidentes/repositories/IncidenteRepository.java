package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenteRepository extends JpaRepository<Incidente, Long>
{
}
