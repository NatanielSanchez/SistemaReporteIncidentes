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

    @Autowired
    public TecnicoResponseMapper(EspecialidadResponseMapper especialidadResponseMapper)
    {
        this.especialidadResponseMapper = especialidadResponseMapper;
    }

    @Override
    public TecnicoResponseDTO apply(Tecnico tecnico)
    {
        return new TecnicoResponseDTO(tecnico.getIdTecnico(),
                tecnico.getNombre(),
                tecnico.getTipoNotificacion().getTipo(),
                tecnico.getContacto(),
                tecnico.getEspecialidades().stream().map(especialidadResponseMapper).toList());
    }
}
