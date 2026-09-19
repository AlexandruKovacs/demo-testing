package com.titulizacion.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/** Traduce las excepciones de la aplicación a respuestas HTTP consistentes. */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiError> handleNoEncontrado(RecursoNoEncontradoException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidacion(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR",
                "Los datos enviados no son válidos.", detalles);
        return ResponseEntity.badRequest().body(error);
    }
}
