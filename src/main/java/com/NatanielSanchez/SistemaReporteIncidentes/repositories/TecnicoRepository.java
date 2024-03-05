package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long>, JpaSpecificationExecutor<Tecnico>
{
    interface Specs
    {
        // filtra nombres que contienen la expresion del parametro (en upperCase)
        static Specification<Tecnico> nombreLike(String nombre)
        {
            return (tecnico, cq, cb) -> cb.like(tecnico.get("nombre"), "%" + nombre.toUpperCase() + "%");
        }

        // filtra apellidos que contienen la expresion del parametro (en upperCase)
        static Specification<Tecnico> apellidoLike(String apellido)
        {
            return (tecnico, cq, cb) -> cb.like(tecnico.get("apellido"), "%" + apellido.toUpperCase() + "%");
        }

        // filtra las especialidades de los tecnicos mediante ID
        static Specification<Tecnico> hasEspecialidades(List<Long> idEspecialidades)
        {
            return (tecnico, cq, cb) -> {
                Join<Tecnico, Especialidad> tecnicoEspecialidad = tecnico.join("especialidades", JoinType.INNER);

                List<Predicate> predicates = new ArrayList<>();
                idEspecialidades.forEach( id-> predicates.add(cb.equal(tecnicoEspecialidad.get("idEspecialidad"), id)) );

                return cb.and(predicates.toArray(new Predicate[0]));
            };
        }

        // filtra tecnicos que puedan resolver el conjunto de problemas
        static Specification<Tecnico> puedeResolverProblemas(List<Long> idProblemas)
        {
            return (tecnico, cq, cb) -> {
                // tecnico JOIN especialidad
                Join<Tecnico, Especialidad> tecnicoEspecialidad = tecnico.join("especialidades", JoinType.INNER);
                // tecnico JOIN especialidad JOIN problema
                Join<Tecnico, Especialidad> especialidadProblema = tecnicoEspecialidad.join("problemas", JoinType.INNER);

                List<Predicate> predicates = new ArrayList<>();
                idProblemas.forEach( id-> predicates.add(cb.equal(especialidadProblema.get("idProblema"), id)) );

                return cb.and(predicates.toArray(new Predicate[0]));
            };
        }
    }
}
