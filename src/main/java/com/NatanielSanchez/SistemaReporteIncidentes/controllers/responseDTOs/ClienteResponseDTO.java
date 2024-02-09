package com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO
{
    long id;
    String tipoCliente;
    String nombre;
    String email;
    String identificacion;
    long[] idServicios;
}
