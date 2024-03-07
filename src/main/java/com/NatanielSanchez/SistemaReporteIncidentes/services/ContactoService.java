package com.NatanielSanchez.SistemaReporteIncidentes.services;

import com.NatanielSanchez.SistemaReporteIncidentes.controllers.responseDTOs.TipoContactoResponseDTO;
import com.NatanielSanchez.SistemaReporteIncidentes.exceptions.ResourceNotFoundException;
import com.NatanielSanchez.SistemaReporteIncidentes.models.TipoContacto;
import com.NatanielSanchez.SistemaReporteIncidentes.repositories.TipoContactoRepository;
import com.NatanielSanchez.SistemaReporteIncidentes.services.mappers.TipoContactoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class ContactoService
{
    TipoContactoRepository tipoContactoRepository;

    TipoContactoResponseMapper tipoContactoResponseMapper;

    @Autowired
    public ContactoService(TipoContactoRepository tipoContactoRepository, TipoContactoResponseMapper tipoContactoResponseMapper)
    {
        this.tipoContactoRepository = tipoContactoRepository;
        this.tipoContactoResponseMapper = tipoContactoResponseMapper;
    }


    public List<TipoContactoResponseDTO> getAllTipoContactos()
    {


        return tipoContactoRepository.findAll().stream()
                .map(tipoContactoResponseMapper)
                .toList();
    }

    public TipoContactoResponseDTO getTipoContactoById(Long id)
    {
        TipoContacto tipoContacto = tipoContactoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TIPO CONTACTO ID: " + id));

        return tipoContactoResponseMapper.apply(tipoContacto);
    }

    public TipoContactoResponseDTO getTipoContactoByTipo(String tipo)
    {
        TipoContacto tipoContacto = tipoContactoRepository.findByTipo(tipo)
                .orElseThrow(()-> new ResourceNotFoundException("TIPO CONTACTO: " + tipo));

        return tipoContactoResponseMapper.apply(tipoContacto);
    }
}
