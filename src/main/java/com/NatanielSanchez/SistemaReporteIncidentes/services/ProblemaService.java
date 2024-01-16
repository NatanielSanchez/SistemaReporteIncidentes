package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ProblemaRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ProblemaUpdateRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ProblemaResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Problema;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ProblemaRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ProblemaResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemaService
{
    private ProblemaRepository problemaRepository;
    private ServicioRepository servicioRepository;
    private ProblemaResponseMapper mapper;

    @Autowired
    public ProblemaService(ProblemaRepository problemaRepository, ServicioRepository servicioRepository, ProblemaResponseMapper mapper)
    {
        this.problemaRepository = problemaRepository;
        this.servicioRepository = servicioRepository;
        this.mapper = mapper;
    }

    public List<ProblemaResponseDTO> getAllProblemas()
    {
        return problemaRepository.findAll().stream()
                .map(mapper)
                .toList();
    }

    public ProblemaResponseDTO getProblemaById(long id)
    {
        Problema p = problemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + id));

        return mapper.apply(p);
    }


    public ProblemaResponseDTO addProblema(ProblemaRequestDTO dto)
    {
        Servicio s = servicioRepository.findById(dto.getId_servicio())
                .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + dto.getId_servicio()));

        Problema p = new Problema();
        p.setTipo(dto.getTipo().toUpperCase());
        p.setDescripcion(dto.getDescripcion().toUpperCase());
        p.setTiempo_maximo_resolucion(dto.getTiempo_maximo_resolucion());
        p.setComplejo(dto.isComplejo());

        s.getProblemas().add(p);
        servicioRepository.save(s);
        return mapper.apply(p);
    }

    public ProblemaResponseDTO updateProblema(long id_problema, ProblemaUpdateRequestDTO dto)
    {
        Problema p = problemaRepository.findById(id_problema)
                .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + id_problema));

        p.setTipo(dto.getTipo().toUpperCase());
        p.setDescripcion(dto.getDescripcion().toUpperCase());
        p.setTiempo_maximo_resolucion(dto.getTiempo_maximo_resolucion());
        p.setComplejo(dto.isComplejo());

        problemaRepository.save(p);
        return mapper.apply(p);
    }

    public ProblemaResponseDTO deleteProblema(long id)
    {
        Problema p = problemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + id));

        problemaRepository.delete(p);
        return mapper.apply(p);
    }
}
