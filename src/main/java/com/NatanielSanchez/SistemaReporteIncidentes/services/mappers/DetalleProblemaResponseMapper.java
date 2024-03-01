package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.DetalleProblemaResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.DetalleProblema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DetalleProblemaResponseMapper implements Function<DetalleProblema, DetalleProblemaResponseDTO>
{
    private ProblemaResponseMapper problemaResponseMapper;
    @Autowired
    public DetalleProblemaResponseMapper(ProblemaResponseMapper problemaResponseMapper)
    {
        this.problemaResponseMapper = problemaResponseMapper;
    }

    @Override
    public DetalleProblemaResponseDTO apply(DetalleProblema detalleProblema)
    {
        long[] estimaciones = new long[detalleProblema.getEstimaciones().size()];

        for(int i=0; i< detalleProblema.getEstimaciones().size(); i++)
        {
            estimaciones[i] = detalleProblema.getEstimaciones().get(i).getTiempoEstimadoResolucion();
        }

        return new DetalleProblemaResponseDTO(problemaResponseMapper.apply(detalleProblema.getProblema()),
                estimaciones);
    }
}
