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
    private long id_incidente;
    private long id_cliente;
    private long id_servicio;
    private long id_tecnico;
    private List<DetalleProblemaResponseDTO> problemas;
    private String fecha_inicio;
    private boolean resuelto;
    private String fecha_resolucion;
}
