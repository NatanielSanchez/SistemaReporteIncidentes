package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteRequestDTO
{
    @NotEmpty
    private Long idCliente;
    @NotEmpty
    private Long idServicio;
    @NotEmpty
    private Long idTecnico;
    @NotEmpty
    private List<DetalleProblemaRequestDTO> problemas;
}
