package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ServicioRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ServicioResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Problema;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ServicioResponseMapper;
import jakarta.transaction.Transactional;
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

    public ServicioResponseDTO getServicioById(long id) throws ResourceNotFoundException
    {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio ID: " + id));
        return mapper.apply(servicio);
    }

    public ServicioResponseDTO addServicio(ServicioRequestDTO dto)
    {
        List<Problema> lista_problemas = dto.getProblemas().stream()
                .map(x -> new Problema(x.getTipo().toUpperCase(),
                        x.getDescripcion().toUpperCase(), x.getTiempo_maximo_resolucion(),
                        x.isComplejo()))
                .toList();

        Servicio nuevo = new Servicio(dto.getNombre().toUpperCase(), lista_problemas);
        repository.save(nuevo);
        return mapper.apply(nuevo);
    }

    public ServicioResponseDTO updateServicio(long id, ServicioRequestDTO dto)
    {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio ID: " + id));

        List<Problema> lista_problemas = dto.getProblemas().stream()
                .map(x -> new Problema(x.getTipo().toUpperCase(),
                        x.getDescripcion().toUpperCase(), x.getTiempo_maximo_resolucion(),
                        x.isComplejo()))
                .toList();

        servicio.update(dto.getNombre().toUpperCase(), lista_problemas);
        repository.save(servicio);
        return mapper.apply(servicio);
    }

    public ServicioResponseDTO deleteServicio(long id)
    {
        Servicio servicio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio ID: " + id));

        repository.delete(servicio);
        return mapper.apply(servicio);
    }
}
