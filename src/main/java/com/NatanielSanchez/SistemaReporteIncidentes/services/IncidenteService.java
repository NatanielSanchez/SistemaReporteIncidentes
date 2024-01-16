package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.IncidenteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.IncidenteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.*;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.*;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.IncidenteResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidenteService
{
    private IncidenteRepository incidenteRepository;
    private ClienteRepository clienteRepository;
    private ServicioRepository servicioRepository;

    private TecnicoRepository tecnicoRepository;
    private ProblemaRepository problemaRepository;
    private IncidenteResponseMapper incidenteResponseMapper;
    @Autowired
    public IncidenteService(IncidenteRepository incidenteRepository, ClienteRepository clienteRepository, ServicioRepository servicioRepository, TecnicoRepository tecnicoRepository, ProblemaRepository problemaRepository, IncidenteResponseMapper incidenteResponseMapper)
    {
        this.incidenteRepository = incidenteRepository;
        this.clienteRepository = clienteRepository;
        this.servicioRepository = servicioRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.problemaRepository = problemaRepository;
        this.incidenteResponseMapper = incidenteResponseMapper;
    }

    public List<IncidenteResponseDTO> getAll()
    {
        return incidenteRepository.findAll().stream()
                .map(incidenteResponseMapper)
                .toList();
    }

    public IncidenteResponseDTO getById(long id)
    {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("INCIDENTE ID: " + id));

        return incidenteResponseMapper.apply(incidente);
    }

    public IncidenteResponseDTO addIncidente(IncidenteRequestDTO dto)
    {
        Cliente cliente = clienteRepository.findById(dto.getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("CLIENTE ID: " + dto.getId_cliente()));

        Servicio servicio = servicioRepository.findById(dto.getId_servicio())
                .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + dto.getId_servicio()));

        Tecnico tecnico = tecnicoRepository.findById(dto.getId_tecnico())
                .orElseThrow(() -> new ResourceNotFoundException("TECNICO ID: " + dto.getId_tecnico()));

        if (!cliente.getServicios().contains(servicio) || )
        ArrayList<Problema> problemas = new ArrayList<>();

        for(int i = 0; i< dto.getProblemas().size(); i++)
        {

        }
    }
}
