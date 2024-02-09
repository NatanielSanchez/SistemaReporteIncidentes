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
    private long idTecnico;
    private String nombre;
    private String tipoNotificacion;
    private String contacto;
    private List<EspecialidadResponseDTO> especialdades;
}
