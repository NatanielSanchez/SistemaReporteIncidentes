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
    @Column
    private long id_tecnico;

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo_notificacion")
    private TipoNotificacion tipo_notificacion;

    @Column
    private String contacto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tecnico_x_especialidad",
            joinColumns = @JoinColumn(name = "id_tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades;

    public Tecnico(String nombre, TipoNotificacion tipo_notificacion, String contacto, List<Especialidad> especialidades)
    {
        this.nombre = nombre;
        this.tipo_notificacion = tipo_notificacion;
        this.contacto = contacto;
        this.especialidades = especialidades;
    }

    public void update(String nombre, TipoNotificacion tipo_notificacion, String contacto, List<Especialidad> especialidades)
    {
        this.nombre = nombre;
        this.tipo_notificacion = tipo_notificacion;
        this.contacto = contacto;
        this.especialidades = especialidades;
    }
}
