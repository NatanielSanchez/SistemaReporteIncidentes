package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tipos_notificacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoNotificacion implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_notificacion", nullable = false)
    private long idTipoNotificacion;

    @Column
    private String tipo;
}
