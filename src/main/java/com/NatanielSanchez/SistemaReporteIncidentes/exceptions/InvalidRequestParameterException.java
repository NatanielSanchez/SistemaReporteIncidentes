package com.NatanielSanchez.SistemaReporteIncidentes.exceptions;

public class InvalidRequestParameterException extends RuntimeException
{
    public InvalidRequestParameterException(){super();}
    public InvalidRequestParameterException(String msg){super(msg);}
}
