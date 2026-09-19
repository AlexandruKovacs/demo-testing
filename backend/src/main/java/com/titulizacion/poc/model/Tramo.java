package com.titulizacion.poc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Tramo (tranche) de la estructura de capital emitida sobre una cartera:
 * Senior, Mezzanine o Equity, cada uno con su propia prelación de cobro,
 * calificación crediticia y cupón.
 */
@Entity
@Table(name = "tramos")
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTramo tipo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0")
    @Column(name = "porcentaje_estructura", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeEstructura;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "calificacion_crediticia", nullable = false)
    private Calificacion calificacionCrediticia;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "tasa_cupon", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaCupon;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "importe_nominal", nullable = false, precision = 19, scale = 2)
    private BigDecimal importeNominal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartera_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Cartera cartera;

    public Tramo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoTramo getTipo() {
        return tipo;
    }

    public void setTipo(TipoTramo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPorcentajeEstructura() {
        return porcentajeEstructura;
    }

    public void setPorcentajeEstructura(BigDecimal porcentajeEstructura) {
        this.porcentajeEstructura = porcentajeEstructura;
    }

    public Calificacion getCalificacionCrediticia() {
        return calificacionCrediticia;
    }

    public void setCalificacionCrediticia(Calificacion calificacionCrediticia) {
        this.calificacionCrediticia = calificacionCrediticia;
    }

    public BigDecimal getTasaCupon() {
        return tasaCupon;
    }

    public void setTasaCupon(BigDecimal tasaCupon) {
        this.tasaCupon = tasaCupon;
    }

    public BigDecimal getImporteNominal() {
        return importeNominal;
    }

    public void setImporteNominal(BigDecimal importeNominal) {
        this.importeNominal = importeNominal;
    }

    public Cartera getCartera() {
        return cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }
}
