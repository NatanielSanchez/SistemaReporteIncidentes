package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ProblemaServicioRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ServicioRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ServicioUpdateRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ServicioResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.DuplicatedResourceException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ServicioResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService
{
    private final ServicioRepository repository;
    private final ServicioResponseMapper mapper;
    @Autowired
    public ServicioService(ServicioRepository repository, ServicioResponseMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ServicioResponseDTO> getAllServicios()
    {
        return repository.findAll().stream()
                .map(mapper)
                .toList();
    }

    public ServicioResponseDTO getServicioById(Long id) throws ResourceNotFoundException
    {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio ID: " + id));
        return mapper.apply(servicio);
    }

    public ServicioResponseDTO addServicio(ServicioRequestDTO servicioRequestDTO)
    {
        String nombre = servicioRequestDTO.getNombre().toUpperCase();
        if (repository.findByNombre(nombre).isPresent())
            throw new DuplicatedResourceException("SERVICIO \"" + nombre + "\"");

        Servicio servicio = new Servicio(nombre);
        for (ProblemaServicioRequestDTO dto : servicioRequestDTO.getProblemas())
        {
            servicio.crearProblema(dto.getTipo(),
                    dto.getDescripcion(),
                    dto.getTiempoMaximoResolucion(),
                    dto.isComplejo());
        }

        repository.save(servicio);
        return mapper.apply(servicio);
    }

    public ServicioResponseDTO updateServicio(Long id, ServicioUpdateRequestDTO dto)
    {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio ID: " + id));

        String nombre = dto.getNombre().toUpperCase();
        /*
         Si ya se encuentra un servicio con el nombre provisto por el dto,
         y el nombre provisto por el dto NO COINCIDE con el nombre del servicio a actualizar...
        */
        if (repository.findByNombre(nombre).isPresent() && ! servicio.getNombre().equals(nombre))
            throw new DuplicatedResourceException("SERVICIO \"" + nombre + "\"");

        servicio.update(nombre);
        repository.save(servicio);
        return mapper.apply(servicio);
    }

    public ServicioResponseDTO deleteServicio(Long id)
    {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio ID: " + id));

        repository.delete(servicio);
        return mapper.apply(servicio);
    }
}
