package com.titulizacion.poc.dto;

import com.titulizacion.poc.model.EstadoCartera;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CarteraRequest {

    @NotBlank
    @Size(min = 3, max = 150)
    private String nombre;

    @NotNull
    private LocalDate fechaConstitucion;

    @NotBlank
    private String moneda;

    @NotNull
    private EstadoCartera estado;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valorNominalTotal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(LocalDate fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public EstadoCartera getEstado() {
        return estado;
    }

    public void setEstado(EstadoCartera estado) {
        this.estado = estado;
    }

    public BigDecimal getValorNominalTotal() {
        return valorNominalTotal;
    }

    public void setValorNominalTotal(BigDecimal valorNominalTotal) {
        this.valorNominalTotal = valorNominalTotal;
    }
}
