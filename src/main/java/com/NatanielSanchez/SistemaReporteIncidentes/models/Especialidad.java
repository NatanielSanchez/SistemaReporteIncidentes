package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "especialidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id_especialidad;

    @Column
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "especialidad_x_problema",
            joinColumns = @JoinColumn(name = "id_especialidad"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas;

    public Especialidad(String nombre, List<Problema> problemas)
    {
        this.nombre = nombre;
        this.problemas = problemas;
    }

    public void update(String nombre, List<Problema> problemas)
    {
        this.nombre = nombre;
        this.problemas = problemas;
    }

    public boolean esTuProblema(Problema p) {
        return this.problemas.contains(p);
    }
}
