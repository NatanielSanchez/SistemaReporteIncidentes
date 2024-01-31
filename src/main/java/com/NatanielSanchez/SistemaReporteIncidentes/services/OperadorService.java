package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.OperadorRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ServicioResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Cliente;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Problema;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Tecnico;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ProblemaRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TecnicoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ServicioResponseMapper;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.TecnicoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperadorService
{
    ClienteRepository clienteRepository;
    ServicioRepository servicioRepository;
    ServicioResponseMapper servicioResponseMapper;
    TecnicoRepository tecnicoRepository;
    TecnicoResponseMapper tecnicoResponseMapper;
    ProblemaRepository problemaRepository;

    @Autowired
    public OperadorService(ClienteRepository clienteRepository, ServicioRepository servicioRepository, ServicioResponseMapper servicioResponseMapper, TecnicoRepository tecnicoRepository, TecnicoResponseMapper tecnicoResponseMapper, ProblemaRepository problemaRepository)
    {
        this.clienteRepository = clienteRepository;
        this.servicioRepository = servicioRepository;
        this.servicioResponseMapper = servicioResponseMapper;
        this.tecnicoRepository = tecnicoRepository;
        this.tecnicoResponseMapper = tecnicoResponseMapper;
        this.problemaRepository = problemaRepository;
    }

    public List<ServicioResponseDTO> getServiciosByIdentificacionCliente(String identificacion)
    {
        Cliente cliente = clienteRepository.findByIdentificacion(identificacion)
                .orElseThrow(()-> new ResourceNotFoundException("CLIENTE IDENTIFICACION: " + identificacion));

        return cliente.getServicios().stream()
                .map(servicioResponseMapper)
                .toList();
    }

    public List<TecnicoResponseDTO> buscarTecnico(OperadorRequestDTO dto)
    {
        List<Problema> problemas = new ArrayList<>();
        for (Long id : dto.getId_problemas())
        {
            problemas.add( problemaRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("PROBLEMA ID: " + id)) );
        }

        return tecnicoRepository.findAll().stream()
                .filter( t-> problemas.stream().allMatch(t::puedeResolverProblema) )
                .map(tecnicoResponseMapper)
                .toList();
    }
}
