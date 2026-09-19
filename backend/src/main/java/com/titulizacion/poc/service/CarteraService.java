package com.titulizacion.poc.service;

import com.titulizacion.poc.dto.ActivoRequest;
import com.titulizacion.poc.dto.CarteraRequest;
import com.titulizacion.poc.dto.ResumenPlataformaResponse;
import com.titulizacion.poc.dto.TramoRequest;
import com.titulizacion.poc.exception.RecursoNoEncontradoException;
import com.titulizacion.poc.model.*;
import com.titulizacion.poc.repository.ActivoRepository;
import com.titulizacion.poc.repository.CarteraRepository;
import com.titulizacion.poc.repository.TramoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarteraService {

    private final CarteraRepository carteraRepository;
    private final ActivoRepository activoRepository;
    private final TramoRepository tramoRepository;

    public CarteraService(CarteraRepository carteraRepository, ActivoRepository activoRepository,
                           TramoRepository tramoRepository) {
        this.carteraRepository = carteraRepository;
        this.activoRepository = activoRepository;
        this.tramoRepository = tramoRepository;
    }

    @Transactional(readOnly = true)
    public List<Cartera> listar() {
        return carteraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cartera obtener(Long id) {
        return carteraRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe una cartera con id " + id));
    }

    public Cartera crear(CarteraRequest request) {
        Cartera cartera = new Cartera();
        aplicarDatos(cartera, request);
        return carteraRepository.save(cartera);
    }

    public Cartera actualizar(Long id, CarteraRequest request) {
        Cartera cartera = obtener(id);
        aplicarDatos(cartera, request);
        return carteraRepository.save(cartera);
    }

    public void eliminar(Long id) {
        if (!carteraRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe una cartera con id " + id);
        }
        carteraRepository.deleteById(id);
    }

    public Activo agregarActivo(Long carteraId, ActivoRequest request) {
        Cartera cartera = obtener(carteraId);
        Activo activo = new Activo();
        activo.setTipoActivo(request.getTipoActivo());
        activo.setValorNominal(request.getValorNominal());
        activo.setTasaInteres(request.getTasaInteres());
        activo.setPlazoMeses(request.getPlazoMeses());
        activo.setCalificacionRiesgo(request.getCalificacionRiesgo());
        cartera.addActivo(activo);
        carteraRepository.save(cartera);
        return activo;
    }

    public Tramo agregarTramo(Long carteraId, TramoRequest request) {
        Cartera cartera = obtener(carteraId);
        Tramo tramo = new Tramo();
        tramo.setNombre(request.getNombre());
        tramo.setTipo(request.getTipo());
        tramo.setPorcentajeEstructura(request.getPorcentajeEstructura());
        tramo.setCalificacionCrediticia(request.getCalificacionCrediticia());
        tramo.setTasaCupon(request.getTasaCupon());
        tramo.setImporteNominal(request.getImporteNominal());
        cartera.addTramo(tramo);
        carteraRepository.save(cartera);
        return tramo;
    }

    @Transactional(readOnly = true)
    public ResumenPlataformaResponse resumen() {
        List<Cartera> carteras = carteraRepository.findAll();

        BigDecimal valorTotal = carteras.stream()
                .map(Cartera::getValorNominalTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long numeroActivos = activoRepository.count();

        Map<String, Long> porTipoTramo = Arrays.stream(TipoTramo.values())
                .collect(Collectors.toMap(Enum::name, tipo -> tramoRepository.findAll().stream()
                        .filter(t -> t.getTipo() == tipo)
                        .count()));

        Map<String, Long> porEstado = Arrays.stream(EstadoCartera.values())
                .collect(Collectors.toMap(Enum::name, estado -> carteras.stream()
                        .filter(c -> c.getEstado() == estado)
                        .count()));

        return new ResumenPlataformaResponse(carteras.size(), valorTotal, numeroActivos, porTipoTramo, porEstado);
    }

    private void aplicarDatos(Cartera cartera, CarteraRequest request) {
        cartera.setNombre(request.getNombre());
        cartera.setFechaConstitucion(request.getFechaConstitucion());
        cartera.setMoneda(request.getMoneda());
        cartera.setEstado(request.getEstado());
        cartera.setValorNominalTotal(request.getValorNominalTotal());
    }
}
