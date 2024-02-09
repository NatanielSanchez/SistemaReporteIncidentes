package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "tiempo_estimado_x_problema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiempoEstimadoResolucion implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estimacion")
    private Long idEstimacion;

    @Column(name = "tiempo_estimado_resolucion")
    private Long tiempoEstimadoResolucion;

    public TiempoEstimadoResolucion(Long tiempoEstimadoResolucion) {
        this.tiempoEstimadoResolucion = tiempoEstimadoResolucion;
    }
}
