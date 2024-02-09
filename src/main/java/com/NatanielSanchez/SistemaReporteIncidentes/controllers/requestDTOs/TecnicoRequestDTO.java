package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoRequestDTO
{
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String tipoNotificacion;
    @NotEmpty
    private String contacto;
    @NotEmpty
    private List<Long> idEspecialidades;
}
