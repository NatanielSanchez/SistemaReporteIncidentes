package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.DetalleProblemaResponseDTO;
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
    private long id_cliente;
    @NotEmpty
    private long id_servicio;
    @NotEmpty
    private long id_tecnico;
    @NotEmpty
    private List<DetalleProblemaRequestDTO> problemas;
}
