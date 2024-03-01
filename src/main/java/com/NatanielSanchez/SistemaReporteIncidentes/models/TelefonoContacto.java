package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("TELEFONO")
@Data
@EqualsAndHashCode(callSuper = true)
public class TelefonoContacto extends Contacto
{
    public TelefonoContacto()
    {
        super();
    }

    public TelefonoContacto(String contacto)
    {
        super(contacto);
    }

    @Override
    public void notificar()
    {
        System.out.println(" * MENSAJE ENVIADO AL TELEFONO " + contacto);
    }

    @Override
    public String getTipo()
    {
        return "TELEFONO";
    }

    @Override
    public String getRegex()
    {
        return "";
    }
}