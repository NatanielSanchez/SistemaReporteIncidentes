package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemaResponseDTO
{
    long idProblema;
    String tipo;
    String descripcion;
    String tiempoMaximoResolucion;
    boolean complejo;
}
