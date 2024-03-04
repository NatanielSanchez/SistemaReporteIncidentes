package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ContactoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Contacto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContactoResponseMapper implements Function<Contacto, ContactoResponseDTO>
{

    @Override
    public ContactoResponseDTO apply(Contacto contacto)
    {
        return new ContactoResponseDTO(contacto.getTipoContacto().getTipo(),
                contacto.getContacto());
    }
}
