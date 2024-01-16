package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadResponseDTO
{
    private long id_especialidad;
    private String nombre;
    private List<ProblemaResponseDTO> problemas;
}
