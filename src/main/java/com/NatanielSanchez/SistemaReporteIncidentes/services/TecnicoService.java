package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.TecnicoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoNotificacion;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.EspecialidadRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TecnicoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoNotificacionRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.TecnicoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TecnicoService
{
    TecnicoRepository tecnicoRepository;
    TipoNotificacionRepository tipoNotificacionRepository;
    EspecialidadRepository especialidadRepository;
    TecnicoResponseMapper mapper;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository, TipoNotificacionRepository tipoNotificacionRepository, EspecialidadRepository especialidadRepository, TecnicoResponseMapper mapper)
    {
        this.tecnicoRepository = tecnicoRepository;
        this.tipoNotificacionRepository = tipoNotificacionRepository;
        this.especialidadRepository = especialidadRepository;
        this.mapper = mapper;
    }

    public List<TecnicoResponseDTO> getAllTecnicos()
    {
        return tecnicoRepository.findAll().stream()
                .map(mapper)
                .toList();
    }

    public TecnicoResponseDTO getTecnicoById(long id)
    {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TECNICO ID: " + id));
        return mapper.apply(tecnico);
    }

    public TecnicoResponseDTO addTecnico(TecnicoRequestDTO dto)
    {
        TipoNotificacion tipoNotificacion = tipoNotificacionRepository.findByTipo(dto.getTipo_notificacion().toUpperCase())
                .orElseThrow(()-> new ResourceNotFoundException("TIPO NOTIFICACION: " + dto.getTipo_notificacion().toUpperCase()));

        List<Especialidad> especialidades = new ArrayList<>();
        for(int i = 0; i < dto.getId_especialidades().length; i++)
        {
            long id_especialidad = dto.getId_especialidades()[i];
            Especialidad especialidad = especialidadRepository.findById(id_especialidad)
                    .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id_especialidad));
            especialidades.add(especialidad);
        }

        Tecnico tecnico = new Tecnico(dto.getNombre().toUpperCase(),
                tipoNotificacion,
                dto.getContacto().toUpperCase(),
                especialidades);

        tecnicoRepository.save(tecnico);
        return mapper.apply(tecnico);
    }


    public TecnicoResponseDTO updateTecnico(long id_tecnico, TecnicoRequestDTO dto)
    {
        Tecnico tecnico = tecnicoRepository.findById(id_tecnico)
                .orElseThrow(()-> new ResourceNotFoundException("TECNICO ID: " + id_tecnico));

        TipoNotificacion tipoNotificacion = tipoNotificacionRepository.findByTipo(dto.getTipo_notificacion().toUpperCase())
                .orElseThrow(()-> new ResourceNotFoundException("TIPO NOTIFICACION: " + dto.getTipo_notificacion().toUpperCase()));

        List<Especialidad> especialidades = new ArrayList<>();
        for(int i = 0; i < dto.getId_especialidades().length; i++)
        {
            long id_especialidad = dto.getId_especialidades()[i];
            Especialidad especialidad = especialidadRepository.findById(id_especialidad)
                    .orElseThrow(()-> new ResourceNotFoundException("ESPECIALIDAD ID: " + id_especialidad));
            especialidades.add(especialidad);
        }

        tecnico.update(dto.getNombre().toUpperCase(),
                tipoNotificacion,
                dto.getContacto().toUpperCase(),
                especialidades);
        tecnicoRepository.save(tecnico);
        return mapper.apply(tecnico);
    }

    public TecnicoResponseDTO deleteTecnico(long id)
    {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TECNICO ID: " + id));

        tecnicoRepository.delete(tecnico);
        return mapper.apply(tecnico);
    }
}