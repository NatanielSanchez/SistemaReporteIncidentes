package com.NatanielSanchez.SistemaReporteIncidentes.util;

import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import org.springframework.data.jpa.domain.Specification;

public class TecnicoSpecs
{
    public static Specification<Tecnico> nombreLike(String nombre)
    {
        return (tecnico, cq, cb) -> cb.like(tecnico.get("nombre"), "%" + nombre + "%");
    }

    public static Specification<Tecnico> apellidoLike(String apellido)
    {
        return (tecnico, cq, cb) -> cb.like(tecnico.get("apellido"), "%" + apellido + "%");
    }
}
