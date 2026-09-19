package com.titulizacion.poc.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ResumenPlataformaResponse {

    private long numeroCarteras;
    private BigDecimal valorTotalTitulizado;
    private long numeroActivos;
    private Map<String, Long> distribucionPorTipoTramo;
    private Map<String, Long> distribucionPorEstado;

    public ResumenPlataformaResponse(long numeroCarteras, BigDecimal valorTotalTitulizado, long numeroActivos,
                                      Map<String, Long> distribucionPorTipoTramo, Map<String, Long> distribucionPorEstado) {
        this.numeroCarteras = numeroCarteras;
        this.valorTotalTitulizado = valorTotalTitulizado;
        this.numeroActivos = numeroActivos;
        this.distribucionPorTipoTramo = distribucionPorTipoTramo;
        this.distribucionPorEstado = distribucionPorEstado;
    }

    public long getNumeroCarteras() {
        return numeroCarteras;
    }

    public BigDecimal getValorTotalTitulizado() {
        return valorTotalTitulizado;
    }

    public long getNumeroActivos() {
        return numeroActivos;
    }

    public Map<String, Long> getDistribucionPorTipoTramo() {
        return distribucionPorTipoTramo;
    }

    public Map<String, Long> getDistribucionPorEstado() {
        return distribucionPorEstado;
    }
}
