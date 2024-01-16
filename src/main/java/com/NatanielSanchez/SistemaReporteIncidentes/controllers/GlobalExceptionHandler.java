package com.NatanielSanchez.SistemaReporteIncidentes.controllers;

import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.DuplicatedResourceException;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<String>("The resource: \n" + ex.getMessage() + "\n was not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedResourceException.class)
    public ResponseEntity<String> handleDuplicatedResource(DuplicatedResourceException ex) {
        return new ResponseEntity<String>("The resource: \n" + ex.getMessage() + "\n already exists.", HttpStatus.CONFLICT);
    }
}
