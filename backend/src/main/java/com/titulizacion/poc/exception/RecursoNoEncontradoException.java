package com.titulizacion.poc.exception;

/** Se lanza cuando se solicita una cartera, activo o tramo que no existe. */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
