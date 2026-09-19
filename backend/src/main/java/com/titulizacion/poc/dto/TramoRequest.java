package com.titulizacion.poc.dto;

import com.titulizacion.poc.model.Calificacion;
import com.titulizacion.poc.model.TipoTramo;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TramoRequest {

    @NotBlank
    private String nombre;

    @NotNull
    private TipoTramo tipo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0")
    private BigDecimal porcentajeEstructura;

    @NotNull
    private Calificacion calificacionCrediticia;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal tasaCupon;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal importeNominal;

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
}
