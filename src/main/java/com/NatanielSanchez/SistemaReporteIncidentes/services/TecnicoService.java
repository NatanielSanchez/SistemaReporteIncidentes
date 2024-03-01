package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.TecnicoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.EspecialidadRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TecnicoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.TecnicoResponseMapper;
import com.NatanielSanchez.SistemaReporteIncidentes.util.ContactoFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService
{
    TecnicoRepository tecnicoRepository;
    EspecialidadRepository especialidadRepository;
    TecnicoResponseMapper tecnicoResponseMapper;
    ContactoFactory contactoFactory;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository, EspecialidadRepository especialidadRepository, TecnicoResponseMapper tecnicoResponseMapper, ContactoFactory contactoFactory)
    {
        this.tecnicoRepository = tecnicoRepository;
        this.especialidadRepository = especialidadRepository;
        this.tecnicoResponseMapper = tecnicoResponseMapper;
        this.contactoFactory = contactoFactory;
    }

    public List<TecnicoResponseDTO> getAllTecnicos()
    {
        return tecnicoRepository.findAll().stream()
                .map(tecnicoResponseMapper)
                .toList();
    }

    public TecnicoResponseDTO getTecnicoById(Long id)
    {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TECNICO ID: " + id));
        return tecnicoResponseMapper.apply(tecnico);
    }

    @Transactional
    public TecnicoResponseDTO addTecnico(TecnicoRequestDTO dto)
    {

        List<Especialidad> especialidades = dto.getIdEspecialidades().stream()
                .map(id -> especialidadRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id)))
                .toList();

        Tecnico tecnico = new Tecnico(dto.getNombre().toUpperCase(),
                dto.getApellido().toUpperCase(),
                dto.getContactos().stream().map(contactoFactory).toList(),
                especialidades);

        tecnicoRepository.save(tecnico);
        return tecnicoResponseMapper.apply(tecnico);
    }


    public TecnicoResponseDTO updateTecnico(Long id_tecnico, TecnicoRequestDTO dto)
    {
        Tecnico tecnico = tecnicoRepository.findById(id_tecnico)
                .orElseThrow(()-> new ResourceNotFoundException("TECNICO ID: " + id_tecnico));

        List<Especialidad> especialidades = dto.getIdEspecialidades().stream()
                .map(id -> especialidadRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id)))
                .toList();

        tecnico.update(dto.getNombre().toUpperCase(),
                dto.getApellido().toUpperCase(),
                dto.getContactos().stream().map(contactoFactory).toList(),
                especialidades);

        tecnicoRepository.save(tecnico);
        return tecnicoResponseMapper.apply(tecnico);
    }

    public TecnicoResponseDTO deleteTecnico(Long id)
    {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TECNICO ID: " + id));

        tecnicoRepository.delete(tecnico);
        return tecnicoResponseMapper.apply(tecnico);
    }
}
