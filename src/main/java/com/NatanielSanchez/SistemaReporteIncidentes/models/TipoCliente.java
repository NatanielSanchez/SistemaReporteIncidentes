package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tipos_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id_tipo_cliente;

    @Column
    private String tipo;

    public TipoCliente(String tipo)
    {
        this.tipo = tipo;
    }
}
