package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import com.NatanielSanchez.SistemaReporteIncidentes.models.TiempoEstimadoResolucion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleProblemaResponseDTO
{
    private ProblemaResponseDTO problema;
    private long[] estimaciones;
}
