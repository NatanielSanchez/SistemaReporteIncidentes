package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ClienteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ClienteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Cliente;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Contacto;
import com.NatanielSanchez.SistemaReporteIncidentes.models.Servicio;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoCliente;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ClienteResponseMapper;
import com.NatanielSanchez.SistemaReporteIncidentes.util.ContactoFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService
{
    private ClienteRepository clienteRepository;
    private TipoClienteRepository tipoClienteRepository;
    private ServicioRepository servicioRepository;
    private ClienteResponseMapper clienteResponseMapper;
    private ContactoFactory contactoFactory;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository, ServicioRepository servicioRepository, ClienteResponseMapper clienteResponseMapper, ContactoFactory contactoFactory)
    {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.servicioRepository = servicioRepository;
        this.clienteResponseMapper = clienteResponseMapper;
        this.contactoFactory = contactoFactory;
    }

    public List<ClienteResponseDTO> getAllClientes()
    {
        return clienteRepository.findAll().stream().map(clienteResponseMapper).toList();
    }

    public ClienteResponseDTO getClienteById(Long id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID: " + id));

        return clienteResponseMapper.apply(cliente);
    }

    @Transactional
    public ClienteResponseDTO addCliente(ClienteRequestDTO dto)
    {
        List<Servicio> servicios = dto.getIdServicios().stream()
                .map(id-> servicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + id)))
                .toList();

        // ! Creo una lista de contactos con el ContactoFactory !
        List<Contacto> contactos = dto.getContactos().stream()
                .map(contactoFactory)
                .toList();

        Cliente cliente = new Cliente(findTipoClienteByTipo(dto.getTipoCliente().toUpperCase()),
                dto.getNombre().toUpperCase(),
                dto.getIdentificacion().toUpperCase(),
                contactos,
                servicios);

        clienteRepository.save(cliente);
        return clienteResponseMapper.apply(cliente);
    }

    public ClienteResponseDTO updateCliente(Long id_cliente, ClienteRequestDTO dto)
    {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID: " + id_cliente));

        List<Servicio> servicios = dto.getIdServicios().stream()
                .map(id-> servicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + id)))
                .toList();

        // ! Creo una lista de contactos con el ContactoFactory !
        List<Contacto> contactos = dto.getContactos().stream()
                .map(contactoFactory)
                .toList();

        cliente.update(findTipoClienteByTipo(dto.getTipoCliente().toUpperCase()),
                dto.getNombre().toUpperCase(),
                dto.getIdentificacion().toUpperCase(),
                contactos,
                servicios);

        clienteRepository.save(cliente);
        return clienteResponseMapper.apply(cliente);
    }

    public ClienteResponseDTO deleteCliente(Long id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID: " + id));

        clienteRepository.delete(cliente);
        return clienteResponseMapper.apply(cliente);
    }

    private TipoCliente findTipoClienteByTipo(String tipo)
    {
        return tipoClienteRepository.findByTipo(tipo)
                .orElseThrow(()-> new ResourceNotFoundException("TIPO CLIENTE: " + tipo));
    }
}
