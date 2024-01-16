package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Problema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemaRepository extends JpaRepository<Problema, Long>
{
    @Query(value = "SELECT s.id_servicio FROM Servicio s JOIN s.problemas p WHERE p.id_problema = :id_problema")
    long findIdServicioByIdProblema(@Param("id_problema") long id_problema);
}
