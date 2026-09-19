package com.titulizacion.poc.config;

import com.titulizacion.poc.model.*;
import com.titulizacion.poc.repository.CarteraRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Carga datos de ejemplo al arrancar la aplicación para que la demo
 * y los tests E2E dispongan de contenido representativo desde el primer momento.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final CarteraRepository carteraRepository;

    public DataSeeder(CarteraRepository carteraRepository) {
        this.carteraRepository = carteraRepository;
    }

    @Override
    public void run(String... args) {
        if (carteraRepository.count() > 0) {
            return;
        }

        Cartera rmbs = new Cartera();
        rmbs.setNombre("RMBS Prado Serie 2025-1");
        rmbs.setFechaConstitucion(LocalDate.of(2025, 6, 12));
        rmbs.setMoneda("EUR");
        rmbs.setEstado(EstadoCartera.ACTIVA);
        rmbs.setValorNominalTotal(new BigDecimal("50000000.00"));

        Activo hipoteca1 = activo(TipoActivo.HIPOTECARIO, "220000.00", "2.90", 300, Calificacion.A);
        Activo hipoteca2 = activo(TipoActivo.HIPOTECARIO, "180000.00", "3.10", 240, Calificacion.BBB);
        rmbs.addActivo(hipoteca1);
        rmbs.addActivo(hipoteca2);

        rmbs.addTramo(tramo("Tramo Senior A", TipoTramo.SENIOR, "80.00", Calificacion.AAA, "2.10", "40000000.00"));
        rmbs.addTramo(tramo("Tramo Mezzanine B", TipoTramo.MEZZANINE, "14.00", Calificacion.BBB, "4.50", "7000000.00"));
        rmbs.addTramo(tramo("Tramo Equity", TipoTramo.EQUITY, "6.00", Calificacion.CCC, "9.00", "3000000.00"));

        carteraRepository.save(rmbs);

        Cartera consumo = new Cartera();
        consumo.setNombre("Fondo Consumo Meridiano 2026-1");
        consumo.setFechaConstitucion(LocalDate.of(2026, 2, 3));
        consumo.setMoneda("EUR");
        consumo.setEstado(EstadoCartera.EN_CONSTITUCION);
        consumo.setValorNominalTotal(new BigDecimal("18000000.00"));

        consumo.addActivo(activo(TipoActivo.CONSUMO, "12000.00", "6.40", 48, Calificacion.BB));
        consumo.addActivo(activo(TipoActivo.PYME, "95000.00", "4.80", 84, Calificacion.BBB));

        consumo.addTramo(tramo("Tramo Senior Único", TipoTramo.SENIOR, "70.00", Calificacion.AA, "3.20", "12600000.00"));
        consumo.addTramo(tramo("Tramo Equity Originador", TipoTramo.EQUITY, "30.00", Calificacion.B, "8.00", "5400000.00"));

        carteraRepository.save(consumo);

        Cartera leasing = new Cartera();
        leasing.setNombre("Leasing Industrial Atlas 2023-2");
        leasing.setFechaConstitucion(LocalDate.of(2023, 11, 20));
        leasing.setMoneda("EUR");
        leasing.setEstado(EstadoCartera.AMORTIZADA);
        leasing.setValorNominalTotal(new BigDecimal("9500000.00"));

        leasing.addActivo(activo(TipoActivo.LEASING, "310000.00", "3.75", 60, Calificacion.A));

        leasing.addTramo(tramo("Tramo Senior", TipoTramo.SENIOR, "85.00", Calificacion.AAA, "2.40", "8075000.00"));
        leasing.addTramo(tramo("Tramo Equity", TipoTramo.EQUITY, "15.00", Calificacion.CCC, "7.50", "1425000.00"));

        carteraRepository.save(leasing);
    }

    private Activo activo(TipoActivo tipo, String valorNominal, String tasa, int plazoMeses, Calificacion calificacion) {
        Activo a = new Activo();
        a.setTipoActivo(tipo);
        a.setValorNominal(new BigDecimal(valorNominal));
        a.setTasaInteres(new BigDecimal(tasa));
        a.setPlazoMeses(plazoMeses);
        a.setCalificacionRiesgo(calificacion);
        return a;
    }

    private Tramo tramo(String nombre, TipoTramo tipo, String porcentaje, Calificacion calificacion, String cupon, String importe) {
        Tramo t = new Tramo();
        t.setNombre(nombre);
        t.setTipo(tipo);
        t.setPorcentajeEstructura(new BigDecimal(porcentaje));
        t.setCalificacionCrediticia(calificacion);
        t.setTasaCupon(new BigDecimal(cupon));
        t.setImporteNominal(new BigDecimal(importe));
        return t;
    }
}
