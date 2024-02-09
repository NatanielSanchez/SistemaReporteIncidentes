package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    private long idTecnico;

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo_notificacion")
    private TipoNotificacion tipoNotificacion;

    @Column
    private String contacto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tecnico_x_especialidad",
            joinColumns = @JoinColumn(name = "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades;

    public Tecnico(String nombre, TipoNotificacion tipoNotificacion, String contacto, List<Especialidad> especialidades)
    {
        this.nombre = nombre;
        this.tipoNotificacion = tipoNotificacion;
        this.contacto = contacto;
        this.especialidades = especialidades;
    }

    public void update(String nombre, TipoNotificacion tipoNotificacion, String contacto, List<Especialidad> especialidades)
    {
        this.nombre = nombre;
        this.tipoNotificacion = tipoNotificacion;
        this.contacto = contacto;
        this.especialidades = especialidades;
    }

    //Verifica si puede resolver un problema particular
    public boolean puedeResolverProblema(Problema p)
    {
        return especialidades.stream().anyMatch((e) -> e.esTuProblema(p));
    }

    // Verifica si puede resolverun conjunto de problemas
    public boolean puedeResolverProblemas(List<Problema> problemas)
    {
        return problemas.stream().allMatch(this::puedeResolverProblema);
    }
}
