package com.NatanielSanchez.SistemaReporteIncidentes.models;

import com.NatanielSanchez.SistemaReporteIncidentes.util.DetalleProblemaEmbeddedId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "detalle_problema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleProblema implements Serializable
{
    @EmbeddedId
    private DetalleProblemaEmbeddedId detalleProblemaEmbeddedId;

    @ManyToOne
    @MapsId("id_incidente")
    @JoinColumn(name = "id_incidente", nullable = false, insertable = false, updatable = false)
    private Incidente incidente;

    @ManyToOne
    @MapsId("id_problema")
    @JoinColumn(name = "id_problema", nullable = false, insertable = false, updatable = false)
    private Problema problema;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "id_incidente", referencedColumnName = "id_incidente"),
            @JoinColumn(name = "id_problema", referencedColumnName = "id_problema")
    })
    private List<TiempoEstimadoResolucion> estimaciones;
}
