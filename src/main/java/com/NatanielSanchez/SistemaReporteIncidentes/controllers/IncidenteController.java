package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.IncidenteRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.IncidenteResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.IncidenteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController
{
    private IncidenteService service;
    @Autowired
    public IncidenteController(IncidenteService service)
    {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<IncidenteResponseDTO>> getAll()
    {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenteResponseDTO> getById(@RequestParam long id)
    {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<IncidenteResponseDTO> addIncidente(@RequestBody IncidenteRequestDTO dto)
    {
        return new ResponseEntity<>(service.addIncidente(dto), HttpStatus.OK);
    }
}
