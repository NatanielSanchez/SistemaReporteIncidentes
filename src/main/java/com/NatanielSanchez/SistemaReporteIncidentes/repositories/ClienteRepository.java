package com.NatanielSanchez.SistemaReporteIncidentes.repositories;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Cliente;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoCliente;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente>
{
    Optional<Cliente> findByIdentificacion(String identificacion);

    interface Specs
    {
        static Specification<Cliente> nombreLike(String nombre)
        {
            return (clienteRoot, cq, cb) ->
                    cb.like(clienteRoot.get("nombre"), "%" + nombre.toUpperCase() + "%");
        }

        static Specification<Cliente> identificacionLike(String identificacion)
        {
            return (clienteRoot, cq, cb) ->
                    cb.like(clienteRoot.get("identificacion"), "%" + identificacion.toUpperCase() + "%");
        }

        static Specification<Cliente> tipoClienteLike(String tipo)
        {
            return (clienteRoot, cq, cb) -> {
                Join<Cliente, TipoCliente> clienteTipoClienteJoin = clienteRoot.join("tipoCliente", JoinType.INNER);
                return cb.like(clienteTipoClienteJoin.get("tipo"), "%" + tipo.toUpperCase() + "%");
            };

        }


    }
}
