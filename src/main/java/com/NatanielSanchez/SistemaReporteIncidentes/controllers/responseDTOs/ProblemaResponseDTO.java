package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemaResponseDTO
{
    long id_problema;
    String tipo;
    String descripcion;
    String tiempo_maximo_resolucion;
    boolean complejo;
}
