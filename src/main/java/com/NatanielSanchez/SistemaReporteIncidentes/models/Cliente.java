package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private long idCliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_cliente")
    private TipoCliente tipoCliente;

    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private String identificacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "servicio_x_cliente",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<Servicio> servicios;

    public Cliente(TipoCliente tipoCliente, String nombre, String email, String identificacion, List<Servicio> servicios)
    {
        this.tipoCliente = tipoCliente;
        this.nombre = nombre;
        this.email = email;
        this.identificacion = identificacion;
        this.servicios = servicios;
    }

    public void update(TipoCliente tipoCliente, String nombre, String email, String identificacion, List<Servicio> servicios)
    {
        this.tipoCliente = tipoCliente;
        this.nombre = nombre;
        this.email = email;
        this.identificacion = identificacion;
        this.servicios = servicios;
    }

    public boolean esTuServicio(Servicio servicio) {
        return this.servicios.contains(servicio);
    }
}
