package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoRequestDTO
{
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String tipo_notificacion;
    @NotEmpty
    private String contacto;
    @NotEmpty
    private long[] id_especialidades;
}
