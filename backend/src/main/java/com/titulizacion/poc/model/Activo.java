package com.titulizacion.poc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Activo subyacente incorporado a una cartera de titulización
 * (por ejemplo, un préstamo hipotecario, de consumo, leasing o PYME).
 */
@Entity
@Table(name = "activos")
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_activo", nullable = false)
    private TipoActivo tipoActivo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "valor_nominal", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorNominal;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "tasa_interes", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteres;

    @NotNull
    @Positive
    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "calificacion_riesgo", nullable = false)
    private Calificacion calificacionRiesgo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartera_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Cartera cartera;

    public Activo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public BigDecimal getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(BigDecimal valorNominal) {
        this.valorNominal = valorNominal;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public Calificacion getCalificacionRiesgo() {
        return calificacionRiesgo;
    }

    public void setCalificacionRiesgo(Calificacion calificacionRiesgo) {
        this.calificacionRiesgo = calificacionRiesgo;
    }

    public Cartera getCartera() {
        return cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }
}
