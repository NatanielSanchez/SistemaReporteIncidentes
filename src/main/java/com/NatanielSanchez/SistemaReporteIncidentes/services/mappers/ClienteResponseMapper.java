package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ClienteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ContactoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Cliente;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ClienteResponseMapper implements Function<Cliente, ClienteResponseDTO>
{
    private ContactoResponseMapper contactoResponseMapper;

    @Autowired
    public ClienteResponseMapper(ContactoResponseMapper contactoResponseMapper)
    {
        this.contactoResponseMapper = contactoResponseMapper;
    }

    @Override
    public ClienteResponseDTO apply(Cliente cliente)
    {
        return new ClienteResponseDTO(cliente.getIdCliente(),
                cliente.getTipoCliente().getTipo(),
                cliente.getNombre(),
                cliente.getIdentificacion(),
                cliente.getContactos().stream().map(contactoResponseMapper).toList(),
                cliente.getServicios().stream().mapToLong(Servicio::getIdServicio).toArray());
    }
}
