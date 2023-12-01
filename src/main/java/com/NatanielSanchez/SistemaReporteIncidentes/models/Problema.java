package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "problemas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problema implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id_problema;

    @Column
    private String tipo;

    @Column
    private String descripcion;

    /*
    La unica forma que encontré de almacenar un tiempo no atado a una fecha es
    simplemente un long que contiene una cantidad de minutos.
    En la tabla se almacena como un BIGINT (equivalente a un long), en minutos tambien.
     */
    @Column
    private long tiempo_maximo_resolucion;

    @Column
    private boolean complejo;

    public Problema(String tipo, String descripcion, long tiempo_maximo_resolucion, boolean complejo)
    {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.tiempo_maximo_resolucion = tiempo_maximo_resolucion;
        this.complejo = complejo;
    }
}
