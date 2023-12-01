package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ServicioRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ServicioResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController
{
    private final ServicioService service;

    @Autowired
    public ServicioController(ServicioService service)
    {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ServicioResponseDTO>> getAllServicios()
    {
        return new ResponseEntity<List<ServicioResponseDTO>>(service.getAllServicios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> getServicioById(@PathVariable long id)
    {
        return new ResponseEntity<ServicioResponseDTO>(service.getServicioById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ServicioResponseDTO> addServicio(@RequestBody ServicioRequestDTO dto)
    {
        return new ResponseEntity<ServicioResponseDTO>(service.addServicio(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> updateServicio(@PathVariable long id, @RequestBody ServicioRequestDTO dto)
    {
        return new ResponseEntity<ServicioResponseDTO>(service.updateServicio(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> deleteServicio(@PathVariable long id)
    {
        return new ResponseEntity<ServicioResponseDTO>(service.deleteServicio(id), HttpStatus.OK);
    }



}
