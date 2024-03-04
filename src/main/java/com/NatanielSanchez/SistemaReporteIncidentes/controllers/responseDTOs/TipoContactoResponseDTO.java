package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoContactoResponseDTO
{
    Long idTipoContacto;
    String tipo;
    String regex;
    String mensajeError;
}
