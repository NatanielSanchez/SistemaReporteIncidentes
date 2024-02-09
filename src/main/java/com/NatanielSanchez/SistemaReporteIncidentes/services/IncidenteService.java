package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.DetalleProblemaRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.IncidenteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.IncidenteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.InvalidRequestParameterException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.*;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.*;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.IncidenteResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public IncidenteResponseDTO getById(Long id)
    {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("INCIDENTE ID: " + id));

        return incidenteResponseMapper.apply(incidente);
    }

    @Transactional
    public IncidenteResponseDTO addIncidente(IncidenteRequestDTO dto)
    {
        //BUSCO AL CLIENTE
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("CLIENTE ID: " + dto.getIdCliente()));

        //BUSCO EL SEVICIO
        Servicio servicio = servicioRepository.findById(dto.getIdServicio())
                .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + dto.getIdServicio()));

        //VERFICO QUE EL CLIENTE ESTE SUBSCRIPTO AL SERVICIO
        if(!cliente.esTuServicio(servicio))
            throw new InvalidRequestParameterException("El cliente con ID: " + cliente.getIdCliente()
                    + " no está subscripto al servicio ID: " + servicio.getIdServicio());

        //BUSCO AL TECNICO
        Tecnico tecnico = tecnicoRepository.findById(dto.getIdTecnico())
                .orElseThrow(() -> new ResourceNotFoundException("TECNICO ID: " + dto.getIdTecnico()));

        //INICIALIZO EL INCIDENTE
        Incidente incidente = new Incidente(cliente, servicio, tecnico, LocalDateTime.now());

        //BUSCO Y VERIFICO LOS PROBLEMAS DEL INCIDENTE
        for(DetalleProblemaRequestDTO dp_dto : dto.getProblemas())
        {
            Problema p = problemaRepository.findById(dp_dto.getIdProblema())
                    .orElseThrow(() -> new ResourceNotFoundException("PROBLEMA ID: " + dp_dto.getIdProblema()));

            //Si el problema no corresponde al servicio, o no puede ser resuelto por el tecnico, TIRA EXCEPCION
            if( !servicio.esTuProblema(p))
            {
                throw new InvalidRequestParameterException("El problema con ID: " + p.getIdProblema()
                        + " no corresponde al servicio con ID: " + servicio.getIdServicio());
            }

            if( !tecnico.puedeResolverProblema(p) )
            {
                throw new InvalidRequestParameterException("El tecnico con ID: " + tecnico.getIdTecnico()
                        + " no puede resolver el problema con ID: " + p.getIdProblema());
            }

            // PREPARO LAS ESTIMACIONES DE RESOLUCION DEL PROBLEMA
            List<TiempoEstimadoResolucion> estimaciones = dp_dto.getEstimaciones().stream()
                    .map(TiempoEstimadoResolucion::new)
                    .toList();

            //CREACION DEL DETALLE_PROBLEMA (delegado al incidente)
            incidente.crearDetalleProblema(p, estimaciones);
        }

        incidenteRepository.save(incidente);
        return incidenteResponseMapper.apply(incidente);
    }

    public IncidenteResponseDTO resolverIncidente(Long id, String mensaje)
    {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("INCIDENTE ID: " + id));

        incidente.resolver(mensaje);
        incidenteRepository.save(incidente);
        return incidenteResponseMapper.apply(incidente);
    }
}
