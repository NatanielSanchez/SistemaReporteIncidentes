package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemaUpdateRequestDTO
{
    @NotEmpty
    private String tipo;

    private String descripcion;

    @NotEmpty
    private long tiempo_maximo_resolucion;

    @NotEmpty
    private boolean complejo;
}
