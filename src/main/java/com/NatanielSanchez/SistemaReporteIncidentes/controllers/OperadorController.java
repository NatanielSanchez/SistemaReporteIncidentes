package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.requestDTOs.OperadorRequestDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.ServicioResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TecnicoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.services.OperadorService;
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

    @GetMapping("/buscarTecnico")
    @ResponseBody
    public ResponseEntity<List<TecnicoResponseDTO>>
        buscarTecnico(@RequestParam(name = "id_problemas") List<Long> idProblemas)
    {
        return new ResponseEntity<>(service.buscarTecnico(idProblemas), HttpStatus.OK);
        //return new ResponseEntity<>(service.buscarTecnicoConSpecs(idProblemas), HttpStatus.OK);
    }
}
