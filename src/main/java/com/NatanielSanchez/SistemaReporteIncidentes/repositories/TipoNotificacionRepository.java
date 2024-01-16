package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, Long>
{
    Optional<TipoNotificacion> findByTipo(String tipo);
}
