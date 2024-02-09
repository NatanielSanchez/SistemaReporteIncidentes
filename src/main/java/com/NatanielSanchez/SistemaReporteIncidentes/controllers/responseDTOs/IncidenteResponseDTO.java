package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteResponseDTO
{
    private long idIncidente;
    private long idCliente;
    private long idServicio;
    private long idTecnico;
    private List<DetalleProblemaResponseDTO> problemas;
    private String fechaInicio;
    private boolean resuelto;
    private String fechaResolucion;
    private String mensaje;
}
