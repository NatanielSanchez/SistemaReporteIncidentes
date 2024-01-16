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
    @Column(nullable = false)
    private long id_tipo_notificacion;

    @Column
    private String tipo;
}
