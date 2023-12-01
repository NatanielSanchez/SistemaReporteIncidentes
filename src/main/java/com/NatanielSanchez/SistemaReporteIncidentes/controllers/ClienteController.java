package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ClienteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ClienteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController
{
    private ClienteService service;

    @Autowired
    public ClienteController(ClienteService service)
    {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes()
    {
        return new ResponseEntity<List<ClienteResponseDTO>>(service.getAllClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable long id)
    {
        return new ResponseEntity<ClienteResponseDTO>(service.getClienteById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ClienteResponseDTO> addCliente(@RequestBody ClienteRequestDTO dto)
    {
        return new ResponseEntity<ClienteResponseDTO>(service.addCliente(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable long id, @RequestBody ClienteRequestDTO dto)
    {
        return new ResponseEntity<ClienteResponseDTO>(service.updateCliente(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> deleteCliente(@PathVariable long id)
    {
        return new ResponseEntity<ClienteResponseDTO>(service.deleteCliente(id), HttpStatus.OK);
    }
}
