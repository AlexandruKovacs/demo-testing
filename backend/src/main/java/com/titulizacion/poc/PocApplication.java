package com.titulizacion.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la plataforma de titulización (PoC).
 *
 * Expone una API REST sobre carteras de titulización, sus activos
 * subyacentes y la estructura de tramos (senior / mezzanine / equity)
 * emitida sobre cada una de ellas.
 */
@SpringBootApplication
public class PocApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocApplication.class, args);
    }
}
