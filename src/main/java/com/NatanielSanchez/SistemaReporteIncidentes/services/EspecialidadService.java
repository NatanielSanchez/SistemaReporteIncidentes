package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.EspecialidadRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.EspecialidadResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.DuplicatedResourceException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Problema;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.EspecialidadRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ProblemaRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.EspecialidadResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService
{
    EspecialidadRepository especialidadRepository;
    ProblemaRepository problemaRepository;
    EspecialidadResponseMapper mapper;
    @Autowired
    public EspecialidadService(EspecialidadRepository especialidadRepository, ProblemaRepository problemaRepository, EspecialidadResponseMapper mapper)
    {
        this.especialidadRepository = especialidadRepository;
        this.problemaRepository = problemaRepository;
        this.mapper = mapper;
    }

    public List<EspecialidadResponseDTO> getAllEspecialidades()
    {
        return especialidadRepository.findAll().stream()
                .map(mapper)
                .toList();
    }

    public EspecialidadResponseDTO getEspecialidadById(Long id)
    {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id));

        return mapper.apply(especialidad);
    }

    public EspecialidadResponseDTO addEspecialidad(EspecialidadRequestDTO dto)
    {
        String nombre = dto.getNombre().toUpperCase();
        if (especialidadRepository.findByNombre(nombre).isPresent())
            throw new DuplicatedResourceException("ESPECIALIDAD NOMBRE: " + nombre);

        List<Problema> problemas = dto.getIdProblemas().stream()
                .map(id-> problemaRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + id)))
                .toList();

        Especialidad especialidad = new Especialidad(nombre, problemas);
        especialidadRepository.save(especialidad);
        return mapper.apply(especialidad);
    }

    public EspecialidadResponseDTO updateEspecialidad(Long id_especialidad, EspecialidadRequestDTO dto)
    {
        Especialidad especialidad = especialidadRepository.findById(id_especialidad)
                .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id_especialidad));

        String nombre = dto.getNombre().toUpperCase();
        /*
        Si ya se encuentra una especialidad con el nombre provisto por el dto,
         y el nombre provisto po el dto NO COINCIDE con el nombre de la especialidad a actualizar...
        */
        if (especialidadRepository.findByNombre(nombre).isPresent() && ! especialidad.getNombre().equals(nombre))
            throw new DuplicatedResourceException("ESPECIALIDAD NOMBRE: " + nombre);

        List<Problema> problemas = dto.getIdProblemas().stream()
                .map(id-> problemaRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + id)))
                .toList();

        especialidad.update(nombre, problemas);
        especialidadRepository.save(especialidad);
        return mapper.apply(especialidad);
    }

    public EspecialidadResponseDTO deleteEspecialiad(Long id)
    {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id));

        especialidadRepository.delete(especialidad);
        return mapper.apply(especialidad);
    }
}
