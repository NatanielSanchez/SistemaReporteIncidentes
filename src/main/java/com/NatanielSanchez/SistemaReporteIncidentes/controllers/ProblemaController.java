package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ProblemaRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.ProblemaUpdateRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ProblemaResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.ProblemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problemas")
public class ProblemaController
{
    private ProblemaService service;
    @Autowired
    public ProblemaController(ProblemaService service)
    {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ProblemaResponseDTO>> getAllProblemas()
    {
        return new ResponseEntity<List<ProblemaResponseDTO>>(service.getAllProblemas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemaResponseDTO> getProblemaById(@PathVariable long id)
    {
        return new ResponseEntity<>(service.getProblemaById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProblemaResponseDTO> addProblema(@RequestBody ProblemaRequestDTO dto)
    {
        return new ResponseEntity<>(service.addProblema(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProblemaResponseDTO> updateProblema(@PathVariable long id, @RequestBody ProblemaUpdateRequestDTO dto)
    {
        return new ResponseEntity<>(service.updateProblema(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProblemaResponseDTO> deleteProblema(@PathVariable long id)
    {
        return new ResponseEntity<>(service.deleteProblema(id), HttpStatus.OK);
    }
}
