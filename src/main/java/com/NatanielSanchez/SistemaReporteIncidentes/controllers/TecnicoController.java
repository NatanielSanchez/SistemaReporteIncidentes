package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.TecnicoRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController
{
    TecnicoService service;
    @Autowired
    public TecnicoController(TecnicoService service)
    {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<TecnicoResponseDTO>> getAllTecnicos()
    {
        return new ResponseEntity<>(service.getAllTecnicos(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<TecnicoResponseDTO> getTecnicoById(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.getTecnicoById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<TecnicoResponseDTO> addTecnico(@RequestBody TecnicoRequestDTO dto)
    {
        return new ResponseEntity<>(service.addTecnico(dto), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<TecnicoResponseDTO> updateTecnico(@PathVariable Long id, @RequestBody TecnicoRequestDTO dto)
    {
        return new ResponseEntity<>(service.updateTecnico(id, dto), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TecnicoResponseDTO> deleteTecnico(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.deleteTecnico(id), HttpStatus.OK);
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<TecnicoResponseDTO>>
        getTecnicosFiltrado(@RequestParam(name = "nombre", required = false) String nombre,
                            @RequestParam(name = "apellido", required = false) String apellido)
    {
        if (nombre != null) nombre = nombre.toUpperCase().strip();
        if (apellido != null) apellido = apellido.toUpperCase().strip();
        return new ResponseEntity<>(service.getTecnicosFiltrado(nombre, apellido), HttpStatus.OK);
    }
}
