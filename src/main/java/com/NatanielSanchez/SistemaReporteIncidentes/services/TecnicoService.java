package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ContactoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.TecnicoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.InvalidRequestParameterException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Contacto;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Especialidad;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoContacto;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.EspecialidadRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TecnicoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoContactoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.TecnicoResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.NatanielSanchez.SistemaReporteIncidentes.util.TecnicoSpecs.*;

@Service
public class TecnicoService
{
    TecnicoRepository tecnicoRepository;
    EspecialidadRepository especialidadRepository;
    TecnicoResponseMapper tecnicoResponseMapper;
    TipoContactoRepository tipoContactoRepository;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository, EspecialidadRepository especialidadRepository, TecnicoResponseMapper tecnicoResponseMapper, TipoContactoRepository tipoContactoRepository)
    {
        this.tecnicoRepository = tecnicoRepository;
        this.especialidadRepository = especialidadRepository;
        this.tecnicoResponseMapper = tecnicoResponseMapper;
        this.tipoContactoRepository = tipoContactoRepository;
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

        Set<Contacto> contactos = dto.getContactos().stream()
                .map(this::crearContacto)
                .collect(Collectors.toSet());

        Tecnico tecnico = new Tecnico(dto.getNombre().toUpperCase(),
                dto.getApellido().toUpperCase(),
                contactos,
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

        Set<Contacto> contactos = dto.getContactos().stream()
                .map(this::crearContacto)
                .collect(Collectors.toSet());

        tecnico.update(dto.getNombre().toUpperCase(),
                dto.getApellido().toUpperCase(),
                contactos,
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

    public List<TecnicoResponseDTO> getTecnicosFiltrado(String nombre, String apellido)
    {
        Specification<Tecnico> spec = Specification.where(null);
        if (nombre != null) spec = spec.or(nombreLike(nombre));
        if (apellido != null) spec = spec.or(apellidoLike(apellido));

        return tecnicoRepository.findAll(spec).stream()
                .map(tecnicoResponseMapper)
                .toList();
    }

    private Contacto crearContacto(ContactoRequestDTO dto)
    {
        TipoContacto tipoContacto = tipoContactoRepository.findByTipo(dto.getTipoContacto().toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("TIPO CONTACTO: " + dto.getTipoContacto()));

        // Si el contacto no cumple con el regex del TipoContacto, salta exception!
        if ( ! tipoContacto.verificarContacto(dto.getContacto()))
            throw new InvalidRequestParameterException(tipoContacto.getMensajeError());

        return new Contacto(tipoContacto, dto.getContacto());
    }
}
