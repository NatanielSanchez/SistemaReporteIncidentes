package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TecnicoResponseMapper implements Function<Tecnico, TecnicoResponseDTO>
{
    private EspecialidadResponseMapper especialidadResponseMapper;
    private ContactoResponseMapper contactoResponseMapper;

    @Autowired
    public TecnicoResponseMapper(EspecialidadResponseMapper especialidadResponseMapper, ContactoResponseMapper contactoResponseMapper)
    {
        this.especialidadResponseMapper = especialidadResponseMapper;
        this.contactoResponseMapper = contactoResponseMapper;
    }

    @Override
    public TecnicoResponseDTO apply(Tecnico tecnico)
    {
        return new TecnicoResponseDTO(tecnico.getIdTecnico(),
                tecnico.getNombre(),
                tecnico.getApellido(),
                tecnico.getContactos().stream().map(contactoResponseMapper).toList(),
                tecnico.getEspecialidades().stream().map(especialidadResponseMapper).toList());
    }
}
