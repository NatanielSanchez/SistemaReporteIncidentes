package com.NatanielSanchez.SistemaReporteIncidentes.util;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleProblemaEmbeddedId implements Serializable
{
    private long id_incidente;
    private long id_problema;
}
