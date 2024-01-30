package com.NatanielSanchez.SistemaReporteIncidentes.util;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable

public class DetalleProblemaEmbeddedId implements Serializable
{
    private long id_incidente;
    private long id_problema;

    @Override
    public boolean equals(Object o) {
        if (o instanceof DetalleProblemaEmbeddedId) {
            DetalleProblemaEmbeddedId id2 = (DetalleProblemaEmbeddedId) o;
            return this.id_incidente == id2.id_incidente && this.id_problema == id2.id_problema;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((Long)id_incidente).hashCode() + ((Long)id_problema).hashCode();
    }

    public long getId_incidente() {
        return id_incidente;
    }

    public void setId_incidente(long id_incidente) {
        this.id_incidente = id_incidente;
    }

    public long getId_problema() {
        return id_problema;
    }

    public void setId_problema(long id_problema) {
        this.id_problema = id_problema;
    }

    public DetalleProblemaEmbeddedId() {
    }

    public DetalleProblemaEmbeddedId(long id_incidente, long id_problema) {
        this.id_incidente = id_incidente;
        this.id_problema = id_problema;
    }
}
