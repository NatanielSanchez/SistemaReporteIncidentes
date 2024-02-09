package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Problema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemaRepository extends JpaRepository<Problema, Long>
{
    /*@Query(value = "SELECT s.idServicio FROM Servicio s JOIN s.problemas p WHERE p.idProblema = :idProblema")
    long findIdServicioByIdProblema(@Param("idProblema") Long idProblema);*/
}
