package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ClienteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ClienteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Cliente;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoCliente;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ClienteResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService
{
    private ClienteRepository clienteRepository;
    private TipoClienteRepository tipoClienteRepository;
    private ServicioRepository servicioRepository;
    private ClienteResponseMapper mapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository, ServicioRepository servicioRepository, ClienteResponseMapper mapper)
    {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.servicioRepository = servicioRepository;
        this.mapper = mapper;
    }

    public List<ClienteResponseDTO> getAllClientes()
    {
        return clienteRepository.findAll().stream().map(mapper).toList();
    }

    public ClienteResponseDTO getClienteById(Long id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID: " + id));

        return mapper.apply(cliente);
    }

    public ClienteResponseDTO addCliente(ClienteRequestDTO dto)
    {
        List<Servicio> servicios = dto.getIdServicios().stream()
                .map(id-> servicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + id)))
                .toList();

        Cliente cliente = new Cliente(findTipoClienteByTipo(dto.getTipoCliente().toUpperCase()),
                dto.getNombre().toUpperCase(),
                dto.getEmail().toUpperCase(),
                dto.getIdentificacion().toUpperCase(),
                servicios);

        clienteRepository.save(cliente);
        return mapper.apply(cliente);
    }

    public ClienteResponseDTO updateCliente(Long id_cliente, ClienteRequestDTO dto)
    {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID: " + id_cliente));

        List<Servicio> servicios = dto.getIdServicios().stream()
                .map(id-> servicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + id)))
                .toList();

        cliente.update(findTipoClienteByTipo(dto.getTipoCliente().toUpperCase()),
                dto.getNombre().toUpperCase(),
                dto.getEmail().toUpperCase(),
                dto.getIdentificacion().toUpperCase(),
                servicios);

        clienteRepository.save(cliente);
        return mapper.apply(cliente);
    }

    public ClienteResponseDTO deleteCliente(Long id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID: " + id));

        clienteRepository.delete(cliente);
        return mapper.apply(cliente);
    }

    private TipoCliente findTipoClienteByTipo(String tipo)
    {
        return tipoClienteRepository.findByTipo(tipo)
                .orElseThrow(()-> new ResourceNotFoundException("TIPO CLIENTE: " + tipo));
    }
}
