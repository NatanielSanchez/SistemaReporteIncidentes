package com.NatanielSanchez.SistemaReporteIncidentes.util;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ContactoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.InvalidRequestParameterException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Contacto;
import com.NatanielSanchez.SistemaReporteIncidentes.models.EmailContacto;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TelefonoContacto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

@Service
public class ContactoFactory implements Function<ContactoRequestDTO, Contacto>
{
    @Override
    public Contacto apply(ContactoRequestDTO dto)
    {
        String tipo = dto.getTipoContacto().toUpperCase();

        if (tipo.equals("TELEFONO")) return new TelefonoContacto(dto.getContacto());
        if (tipo.equals("EMAIL")) return new EmailContacto(dto.getContacto());

        throw new InvalidRequestParameterException("TIPO DE CONTACTO NO PERMITIDO: " + tipo);
    }
}
