package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long>
{
    Optional<Servicio> findByNombre(String nombre);
}
