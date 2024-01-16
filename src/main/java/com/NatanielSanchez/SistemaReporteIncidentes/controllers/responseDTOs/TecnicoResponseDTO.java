package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoResponseDTO
{
    private long id_tecnico;
    private String nombre;
    private String tipo_notificacion;
    private String contacto;
    private List<EspecialidadResponseDTO> especialdades;
}
