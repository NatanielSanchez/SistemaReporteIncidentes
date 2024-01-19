package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.DetalleProblemaRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.IncidenteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.IncidenteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.InvalidRequestParameterException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.*;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.*;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.IncidenteResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private DetalleProblemaRepository detalleProblemaRepository;
    private IncidenteResponseMapper incidenteResponseMapper;

    public IncidenteService(IncidenteRepository incidenteRepository, ClienteRepository clienteRepository, ServicioRepository servicioRepository, TecnicoRepository tecnicoRepository, ProblemaRepository problemaRepository, DetalleProblemaRepository detalleProblemaRepository, IncidenteResponseMapper incidenteResponseMapper)
    {
        this.incidenteRepository = incidenteRepository;
        this.clienteRepository = clienteRepository;
        this.servicioRepository = servicioRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.problemaRepository = problemaRepository;
        this.detalleProblemaRepository = detalleProblemaRepository;
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
        //BUSCO AL CLIENTE
        Cliente cliente = clienteRepository.findById(dto.getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("CLIENTE ID: " + dto.getId_cliente()));

        //BUSCO EL SEVICIO
        Servicio servicio = servicioRepository.findById(dto.getId_servicio())
                .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + dto.getId_servicio()));

        //VERFICO QUE EL CLIENTE ESTE SUBSCRIPTO AL SERVICIO
        if(!cliente.getServicios().contains(servicio))
            throw new InvalidRequestParameterException("El cliente con ID: " + cliente.getId_cliente()
                    + " no está subscripto al servicio ID: " + servicio.getId_servicio());

        //BUSCO AL TECNICO
        Tecnico tecnico = tecnicoRepository.findById(dto.getId_tecnico())
                .orElseThrow(() -> new ResourceNotFoundException("TECNICO ID: " + dto.getId_tecnico()));

        //INICIALIZO EL INCIDENTE
        Incidente incidente = new Incidente();

        //BUSCO Y VERIFICO LOS PROBLEMAS DEL INCIDENTE
        ArrayList<DetalleProblema> detalleProblemas = new ArrayList<>();
        for(DetalleProblemaRequestDTO dp_dto : dto.getProblemas())
        {
            Problema p = problemaRepository.findById(dp_dto.getId_problema())
                    .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + dp_dto.getId_problema()));

            //Si el problema no corresponde al servicio, o no puede ser resuelto por el tecnico, TIRA EXCEPCION
            if( !servicio.getProblemas().contains(p))
            {
                throw new InvalidRequestParameterException("El problema con ID: " + p.getId_problema()
                        + " no corresponde al servicio con ID: " + servicio.getId_servicio());
            }

            if( tecnico.getEspecialidades().stream().noneMatch(x-> x.getProblemas().contains(p)) )
                throw new InvalidRequestParameterException("El tecnico con ID: " + tecnico.getId_tecnico()
                        + " no puede resolver el problema con ID: " + p.getId_problema());

            // PREPARO LAS ESTIMACIONES DE RESOLUCION DEL PROBLEMA
            ArrayList<TiempoEstimadoResolucion> estimaciones = new ArrayList<>();
            for(int i=0; i < dp_dto.getEstimaciones().length; i++)
            {
                TiempoEstimadoResolucion estimacion = new TiempoEstimadoResolucion();
                estimacion.setTiempo_estimado_resolucion(dp_dto.getEstimaciones()[i]);
                estimaciones.add(estimacion);
            }

            //CREACION DEL DETALLE_PROBLEMA
            DetalleProblema detalleProblema = new DetalleProblema();
            detalleProblema.setIncidente(incidente);
            detalleProblema.setProblema(p);
            detalleProblema.setEstimaciones(estimaciones);
            detalleProblemas.add(detalleProblema);
        }

        //CREACION COMPLETA DEL INCIDENTE (hopefully)
        incidente.setCliente(cliente);
        incidente.setServicio(servicio);
        incidente.setTecnico(tecnico);
        incidente.setProblemas(detalleProblemas);
        incidente.setResuelto(false);
        incidente.setFecha_inicio(LocalDateTime.now());
        
        incidenteRepository.save(incidente);
        for(DetalleProblema dp : incidente.getProblemas())
        {
            detalleProblemaRepository.save(dp);
        }
        return incidenteResponseMapper.apply(incidente);
    }
}
