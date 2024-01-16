package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.EspecialidadRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.EspecialidadResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController
{
    EspecialidadService service;
    @Autowired
    public EspecialidadController(EspecialidadService service)
    {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<EspecialidadResponseDTO>> getAllEspecialidades()
    {
        return new ResponseEntity<>(service.getAllEspecialidades(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<EspecialidadResponseDTO> getEspecialidadById(@PathVariable long id)
    {
        return new ResponseEntity<>(service.getEspecialidadById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EspecialidadResponseDTO> addEspecialidad(@RequestBody EspecialidadRequestDTO dto)
    {
        return new ResponseEntity<>(service.addEspecialidad(dto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EspecialidadResponseDTO> updateEspecialidad(@PathVariable long id, @RequestBody EspecialidadRequestDTO dto)
    {
        return new ResponseEntity<>(service.updateEspecialidad(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> deleteEspecialidad(@PathVariable long id)
    {
        return new ResponseEntity<>(service.deleteEspecialiad(id), HttpStatus.OK);
    }


}
