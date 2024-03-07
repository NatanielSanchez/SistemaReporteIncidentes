package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import jakarta.persistence.criteria.CriteriaQuery;
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

                /* Explicacion de la consulta:
                * SELECT t.*
                    FROM tecnicos t
                    JOIN tecnico_x_especialidad txe ON t.id_tecnico = txe.id_tecnico
                    WHERE txe.id_especialidad IN (filtros)
                    GROUP BY t.id_tecnico
                    HAVING COUNT(DISTINCT txe.id_especialidad) = filtros.length;
                *
                * */


                //We get a join entity, that allows us to access the attributes of the joined table.
                Join<Tecnico, Especialidad> tecnicoEspecialidad = tecnico.join("especialidades", JoinType.INNER);

                //We create the predicates of the inClause and the havingClause,
                Predicate inClause = tecnicoEspecialidad.get("idEspecialidad").in(idEspecialidades); //We want the especialidad to be inside the filtered especialidades
                Predicate havingClause = cb.equal(cb.countDistinct(tecnicoEspecialidad.get("idEspecialidad")), idEspecialidades.size());

                //Here, we modify the current query so that it actually gets grouped.
                //Notice that this may override any other havingClause, so we have to be careful when reusing this query.
                cq.groupBy(tecnico.get("idTecnico")).having(havingClause);

                //We return the inClause predicate, that gets added into the array of Predicates in the specs at the service layer.
                return inClause;
            };
        }

        // filtra tecnicos que puedan resolver el conjunto de problemas
        static Specification<Tecnico> puedeResolverProblemas(List<Long> idProblemas)
        {
            /*select t.* from tecnicos t
                join tecnico_x_especialidad txe ON (t.id_tecnico = txe.id_tecnico)
                join especialidades e ON (txe.id_especialidad = e.id_especialidad)
                join especialidad_x_problema exp ON (exp.id_especialidad = e.id_especialidad)
                WHERE exp.id_problema in (filtros)
                GROUP BY t.id_tecnico
                HAVING count(distinct exp.id_problema) = filtros.size;*/

            return (tecnico, cq, cb) -> {
                // tecnico JOIN especialidad
                Join<Tecnico, Especialidad> tecnicoEspecialidad = tecnico.join("especialidades", JoinType.INNER);
                // tecnico JOIN especialidad JOIN problema
                Join<Tecnico, Especialidad> especialidadProblema = tecnicoEspecialidad.join("problemas", JoinType.INNER);

                Predicate inClause = especialidadProblema.get("idProblema").in(idProblemas);
                Predicate havingClause = cb.equal(
                        cb.countDistinct(especialidadProblema.get("idProblema")), idProblemas.size()
                );

                //Here, we modify the current query so that it actually gets grouped.
                cq.groupBy(tecnico.get("idTecnico")).having(havingClause);
                return inClause;
            };
        }
    }
}
