package com.titulizacion.poc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un vehículo/fondo de titulización constituido sobre una
 * agrupación de activos (la "cartera" cedida al fondo).
 */
@Entity
@Table(name = "carteras")
public class Cartera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 150)
    @Column(nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "fecha_constitucion", nullable = false)
    private LocalDate fechaConstitucion;

    @NotBlank
    @Column(nullable = false, length = 3)
    private String moneda;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCartera estado;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "valor_nominal_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorNominalTotal;

    @OneToMany(mappedBy = "cartera", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Activo> activos = new ArrayList<>();

    @OneToMany(mappedBy = "cartera", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tramo> tramos = new ArrayList<>();

    public Cartera() {
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

    public List<Activo> getActivos() {
        return activos;
    }

    public void setActivos(List<Activo> activos) {
        this.activos = activos;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    public void addActivo(Activo activo) {
        activos.add(activo);
        activo.setCartera(this);
    }

    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
        tramo.setCartera(this);
    }
}
