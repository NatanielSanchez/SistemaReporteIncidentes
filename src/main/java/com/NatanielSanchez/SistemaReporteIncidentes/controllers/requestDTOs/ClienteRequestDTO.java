package com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO
{
    @NotEmpty
    String tipo_cliente;
    @NotEmpty
    String nombre;
    @NotEmpty
    String email;
    @NotEmpty
    String identificacion;
    @NotEmpty //absolutely NOT empty
    long[] id_servicios;
}
