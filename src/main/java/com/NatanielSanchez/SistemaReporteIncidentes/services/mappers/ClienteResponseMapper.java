package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ClienteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Cliente;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteResponseMapper implements Function<Cliente, ClienteResponseDTO>
{
    @Override
    public ClienteResponseDTO apply(Cliente cliente)
    {
        return new ClienteResponseDTO(cliente.getIdCliente(),
                cliente.getTipoCliente().getTipo(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getIdentificacion(),
                cliente.getServicios().stream().mapToLong(Servicio::getIdServicio).toArray());
    }
}
