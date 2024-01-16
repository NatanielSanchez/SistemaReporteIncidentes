package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "incidentes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_incidente;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    // LISTA DE "DetalleProblema" !!!
    @OneToMany
    @JoinColumn(name = "id_incidente", nullable = false)
    private List<DetalleProblema> problemas;

    @Column
    private LocalDateTime fecha_inicio;
    @Column
    private boolean resuelto;
    @Column
    private LocalDateTime fecha_resolucion;
}
