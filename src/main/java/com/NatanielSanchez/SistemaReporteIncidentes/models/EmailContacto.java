package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("EMAIL")
@Data
@EqualsAndHashCode(callSuper = true)
public class EmailContacto extends Contacto
{
    public EmailContacto()
    {
        super();
    }

    public EmailContacto(String contacto)
    {
        super(contacto);
    }

    @Override
    public void notificar()
    {
        System.out.println(" * MENSAJE ENVIADO AL EMAIL " + contacto);
    }

    @Override
    public String getTipo()
    {
        return "EMAIL";
    }

    @Override
    public String getRegex()
    {
        return "";
    }


}