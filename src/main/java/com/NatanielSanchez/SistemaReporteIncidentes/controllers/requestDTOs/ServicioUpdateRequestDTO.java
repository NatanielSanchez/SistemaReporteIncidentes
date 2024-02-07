package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioUpdateRequestDTO
{
    @NotEmpty
    private String nombre;
}
