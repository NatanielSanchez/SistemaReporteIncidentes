package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long>
{
    Optional<Especialidad> findByNombre(String nombre);
}
