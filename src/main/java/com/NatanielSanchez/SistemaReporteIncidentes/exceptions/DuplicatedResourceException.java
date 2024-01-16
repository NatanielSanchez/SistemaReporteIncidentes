package com.NatanielSanchez.SistemaReporteIncidentes.exceptions;

public class DuplicatedResourceException extends RuntimeException
{
    public DuplicatedResourceException() {
        super();
    }

    public DuplicatedResourceException(String msg) {
        super(msg);
    }
}
