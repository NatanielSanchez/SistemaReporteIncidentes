package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.DetalleProblema;
import com.NatanielSanchez.SistemaReporteIncidentes.util.DetalleProblemaEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleProblemaRepository extends JpaRepository<DetalleProblema, DetalleProblemaEmbeddedId>
{
}
