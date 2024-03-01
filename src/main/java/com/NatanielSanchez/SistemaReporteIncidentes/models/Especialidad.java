package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    @Column
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "especialidad_x_problema",
            joinColumns = @JoinColumn(name = "id_especialidad"),
            inverseJoinColumns = @JoinColumn(name = "id_problema")
    )
    private List<Problema> problemas = new ArrayList<>();

    public Especialidad(String nombre, List<Problema> problemas)
    {
        this.nombre = nombre;
        this.problemas = problemas;
    }

    public void update(String nombre, List<Problema> problemas)
    {
        this.nombre = nombre;
        setProblemas(problemas);
    }

    public boolean esTuProblema(Problema p) {
        return getProblemas().contains(p);
    }
}
