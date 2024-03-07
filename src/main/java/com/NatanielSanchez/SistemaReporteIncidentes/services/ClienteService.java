package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ClienteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ContactoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ClienteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.InvalidRequestParameterException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.*;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.ServicioRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoClienteRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoContactoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.ClienteResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteService
{
    private ClienteRepository clienteRepository;
    private TipoClienteRepository tipoClienteRepository;
    private ServicioRepository servicioRepository;
    private ClienteResponseMapper clienteResponseMapper;
    private TipoContactoRepository tipoContactoRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository, ServicioRepository servicioRepository, ClienteResponseMapper clienteResponseMapper, TipoContactoRepository tipoContactoRepository)
    {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.servicioRepository = servicioRepository;
        this.clienteResponseMapper = clienteResponseMapper;
        this.tipoContactoRepository = tipoContactoRepository;
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

    public List<ClienteResponseDTO> getClientesFiltrado(String nombre, String identificacion, String tipo)
    {
        Specification<Cliente> spec = Specification.where(null);
        if (nombre != null)
            spec = spec.and(ClienteRepository.Specs.nombreLike(nombre));
        if (identificacion != null)
            spec = spec.and(ClienteRepository.Specs.identificacionLike(identificacion));
        if (tipo != null)
            spec = spec.and(ClienteRepository.Specs.tipoClienteLike(tipo));

        return clienteRepository.findAll(spec).stream()
                .map(clienteResponseMapper)
                .toList();
    }

    @Transactional
    public ClienteResponseDTO addCliente(ClienteRequestDTO dto)
    {
        // Busco los servicios, salta exception si no encuentra alguno...
        List<Servicio> servicios = dto.getIdServicios().stream()
                .map(id-> servicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + id)))
                .toList();

        //Crea los contactos mediante metodo auxiliar crearContacto()
        Set<Contacto> contactos = dto.getContactos().stream()
                .map(this::crearContacto)
                .collect(Collectors.toSet()); // ELIMINA DUPLICADOS DEL STREAM! WOW!

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

        // Busco los servicios, salta exception si no encuentra alguno...
        List<Servicio> servicios = dto.getIdServicios().stream()
                .map(id-> servicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("SERVICIO ID: " + id)))
                .toList();

        //Crea los contactos mediante metodo auxiliar crearContacto()
        Set<Contacto> contactos = dto.getContactos().stream()
                .map(this::crearContacto)
                .collect(Collectors.toSet()); // ELIMINA DUPLICADOS DEL STREAM!

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
