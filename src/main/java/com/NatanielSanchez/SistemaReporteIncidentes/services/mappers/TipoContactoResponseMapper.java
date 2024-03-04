package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TipoContactoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoContacto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TipoContactoResponseMapper implements Function<TipoContacto, TipoContactoResponseDTO>
{

    @Override
    public TipoContactoResponseDTO apply(TipoContacto tipoContacto)
    {
        return new TipoContactoResponseDTO(tipoContacto.getIdTipoContacto(),
                tipoContacto.getTipo(),
                tipoContacto.getRegex().replace("\\\\", "\\"),
                tipoContacto.getMensajeError());
    }
}
