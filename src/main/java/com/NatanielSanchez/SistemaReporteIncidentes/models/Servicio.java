package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio implements Serializable
{
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_servicio;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio", nullable = false)
    private List<Problema> problemas;

    public Servicio(String nombre)
    {
        this.nombre = nombre;
        this.problemas = new ArrayList<Problema>();
    }

    public void update(String nombre)
    {
        this.nombre = nombre;
    }
}
