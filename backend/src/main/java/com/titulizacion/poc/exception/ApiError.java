package com.titulizacion.poc.exception;

import java.time.Instant;
import java.util.List;

/** Estructura uniforme de error devuelta por la API. */
public class ApiError {

    private final Instant timestamp = Instant.now();
    private final int status;
    private final String error;
    private final String message;
    private final List<String> detalles;

    public ApiError(int status, String error, String message, List<String> detalles) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.detalles = detalles;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetalles() {
        return detalles;
    }
}
