package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.EspecialidadResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EspecialidadResponseMapper implements Function<Especialidad, EspecialidadResponseDTO>
{
    private ProblemaResponseMapper problemaResponseMapper;

    @Autowired
    public EspecialidadResponseMapper(ProblemaResponseMapper problemaResponseMapper)
    {
        this.problemaResponseMapper = problemaResponseMapper;
    }

    @Override
    public EspecialidadResponseDTO apply(Especialidad especialidad)
    {
        return new EspecialidadResponseDTO(especialidad.getIdEspecialidad(),
                especialidad.getNombre(),
                especialidad.getProblemas().stream().map(problemaResponseMapper).toList());
    }
}
