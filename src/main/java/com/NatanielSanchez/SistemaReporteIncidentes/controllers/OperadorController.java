package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.OperadorRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ServicioResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.OperadorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrarIncidente")
public class OperadorController
{
    OperadorService service;
    @Autowired
    public OperadorController(OperadorService service)
    {
        this.service = service;
    }

    @GetMapping("/serviciosDeCliente/{identificacion}")
    public ResponseEntity<List<ServicioResponseDTO>>
        getServiciosByIdentificacionCliente(@RequestParam String identificacion)
    {
        return new ResponseEntity<>(service.getServiciosByIdentificacionCliente(identificacion), HttpStatus.OK);
    }

    //RequestBody del paquete org.springframework.web.bind.annotation NO FUNCIONA >:(
    //      parece que el body (el dto) no llega a la peticion ????????????????
    //RequestBody del paquete io.swagger.v3.oas.annotations.parameters SI FUNCIONA :)
    @GetMapping("/buscarTecnico")
    public ResponseEntity<List<TecnicoResponseDTO>>
        buscarTecnico(@RequestBody OperadorRequestDTO dto)
    {
        return new ResponseEntity<>(service.buscarTecnico(dto), HttpStatus.OK);
    }
}
