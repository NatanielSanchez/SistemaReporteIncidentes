package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tecnicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnico")
    private Long idTecnico;

    @Column
    private String nombre;
    @Column
    private String apellido;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tecnico")
    private List<Contacto> contactos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tecnico_x_especialidad",
            joinColumns = @JoinColumn(name = "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades = new ArrayList<>();

    public Tecnico(String nombre, String apellido, List<Contacto> contactos, List<Especialidad> especialidades)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contactos = contactos;
        this.especialidades = especialidades;
    }

    public void update(String nombre, String apellido, List<Contacto> contactos, List<Especialidad> especialidades)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        setContactos(contactos);
        setEspecialidades(especialidades);
    }

    //Verifica si puede resolver un problema particular
    public boolean puedeResolverProblema(Problema p)
    {
        return getEspecialidades().stream().anyMatch((e) -> e.esTuProblema(p));
    }

    // Verifica si puede resolver un conjunto de problemas
    public boolean puedeResolverProblemas(List<Problema> problemas)
    {
        return problemas.stream().allMatch(this::puedeResolverProblema);
    }
}
