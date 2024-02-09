package com.NatanielSanchez.SistemaReporteIncidentes.services.mappers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.DetalleProblemaResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.IncidenteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Incidente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class IncidenteResponseMapper implements Function<Incidente, IncidenteResponseDTO>
{
    private DetalleProblemaResponseMapper dp_mapper;
    @Autowired
    public IncidenteResponseMapper(DetalleProblemaResponseMapper dp_mapper)
    {
        this.dp_mapper = dp_mapper;
    }

    @Override
    public IncidenteResponseDTO apply(Incidente incidente)
    {
        List<DetalleProblemaResponseDTO> lista = incidente.getProblemas().stream()
                .map(dp_mapper)
                .toList();

        String fecha_resolucion;
        if (incidente.isResuelto()) fecha_resolucion = incidente.getFechaResolucion().toString();
        else fecha_resolucion = "------";

        String mensaje = incidente.getMensaje();
        if ( mensaje == null || mensaje.isEmpty() ) mensaje = "------";

        return new IncidenteResponseDTO(incidente.getIdIncidente(),
                incidente.getCliente().getIdCliente(),
                incidente.getServicio().getIdServicio(),
                incidente.getTecnico().getIdTecnico(),
                lista,
                incidente.getFechaInicio().toString(),
                incidente.isResuelto(),
                fecha_resolucion,
                mensaje);
    }
}
