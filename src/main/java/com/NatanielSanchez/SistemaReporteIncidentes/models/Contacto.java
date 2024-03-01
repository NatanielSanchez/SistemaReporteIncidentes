package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@Table(name = "contactos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@DiscriminatorOptions(force = true)
@Data
public abstract class Contacto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    @EqualsAndHashCode.Exclude
    protected Long idContacto;
    @Column
    protected String contacto;

    public Contacto()
    {
    }

    public Contacto(String contacto)
    {
        this.contacto = contacto;
    }

    abstract void notificar();
    public abstract String getTipo();
    public abstract String getRegex();

}