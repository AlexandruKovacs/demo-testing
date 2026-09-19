package com.titulizacion.poc.dto;

import com.titulizacion.poc.model.Calificacion;
import com.titulizacion.poc.model.TipoActivo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ActivoRequest {

    @NotNull
    private TipoActivo tipoActivo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valorNominal;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal tasaInteres;

    @NotNull
    @Positive
    private Integer plazoMeses;

    @NotNull
    private Calificacion calificacionRiesgo;

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
}
