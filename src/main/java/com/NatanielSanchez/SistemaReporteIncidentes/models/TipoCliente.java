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
    @Column(name = "id_tipo_cliente", nullable = false)
    private long idTipoCliente;

    @Column
    private String tipo;

    public TipoCliente(String tipo)
    {
        this.tipo = tipo;
    }
}
