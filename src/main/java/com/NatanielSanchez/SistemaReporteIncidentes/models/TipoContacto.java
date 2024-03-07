package com.NatanielSanchez.SistemaReporteIncidentes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "tipos_contacto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoContacto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contacto")
    private Long idTipoContacto;

    @Column
    private String tipo;
    @Column
    private String regex;
    @Column(name = "mensaje_error")
    private String mensajeError;

    public boolean verificarContacto(String contacto)
    {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contacto);
        
        return matcher.matches();
    }
}
