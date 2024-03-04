package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TipoContactoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contactos")
public class ContactoController
{
    ContactoService service;

    @Autowired
    public ContactoController(ContactoService service)
    {
        this.service = service;
    }

    @GetMapping("/tiposContacto")
    public ResponseEntity<List<TipoContactoResponseDTO>>  getAllTipoContactos()
    {
        return new ResponseEntity<>(service.getAllTipoContactos(), HttpStatus.OK);
    }

    @GetMapping("/tiposContacto/{id}")
    public ResponseEntity<TipoContactoResponseDTO> getTipoContactoById(@RequestParam Long id)
    {
        return new ResponseEntity<>(service.getTipoContactoById(id), HttpStatus.OK);
    }

    @GetMapping("/tiposContacto/tipo/{tipo}")
    public ResponseEntity<TipoContactoResponseDTO> getTipoContactoByTipo(@RequestParam String tipo)
    {
        return new ResponseEntity<>(service.getTipoContactoByTipo(tipo.toUpperCase().strip()), HttpStatus.OK);
    }
}
