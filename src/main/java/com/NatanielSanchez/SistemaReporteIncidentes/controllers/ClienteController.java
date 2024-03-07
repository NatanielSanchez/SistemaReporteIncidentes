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
        return new ResponseEntity<>(service.getAllClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.getClienteById(id), HttpStatus.OK);
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<ClienteResponseDTO>> getClientesFiltrado(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "identificacion", required = false) String identificacion,
            @RequestParam(name = "tipo", required = false) String tipo)
    {
        return new ResponseEntity<>(service.getClientesFiltrado(nombre, identificacion, tipo), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ClienteResponseDTO> addCliente(@RequestBody ClienteRequestDTO dto)
    {
        return new ResponseEntity<>(service.addCliente(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO dto)
    {
        return new ResponseEntity<>(service.updateCliente(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> deleteCliente(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.deleteCliente(id), HttpStatus.OK);
    }
}
