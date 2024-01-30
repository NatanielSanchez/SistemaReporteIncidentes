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
    private long id_estimacion;

    @Column
    private int tiempo_estimado_resolucion;

    public TiempoEstimadoResolucion(int tiempo_estimado_resolucion) {
        this.tiempo_estimado_resolucion = tiempo_estimado_resolucion;
    }
}
