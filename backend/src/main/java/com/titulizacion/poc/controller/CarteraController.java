package com.titulizacion.poc.controller;

import com.titulizacion.poc.dto.ActivoRequest;
import com.titulizacion.poc.dto.CarteraRequest;
import com.titulizacion.poc.model.Activo;
import com.titulizacion.poc.model.Cartera;
import com.titulizacion.poc.model.Tramo;
import com.titulizacion.poc.dto.TramoRequest;
import com.titulizacion.poc.service.CarteraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carteras")
public class CarteraController {

    private final CarteraService carteraService;

    public CarteraController(CarteraService carteraService) {
        this.carteraService = carteraService;
    }

    @GetMapping
    public List<Cartera> listar() {
        return carteraService.listar();
    }

    @GetMapping("/{id}")
    public Cartera obtener(@PathVariable Long id) {
        return carteraService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cartera crear(@Valid @RequestBody CarteraRequest request) {
        return carteraService.crear(request);
    }

    @PutMapping("/{id}")
    public Cartera actualizar(@PathVariable Long id, @Valid @RequestBody CarteraRequest request) {
        return carteraService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        carteraService.eliminar(id);
    }

    @PostMapping("/{id}/activos")
    @ResponseStatus(HttpStatus.CREATED)
    public Activo agregarActivo(@PathVariable Long id, @Valid @RequestBody ActivoRequest request) {
        return carteraService.agregarActivo(id, request);
    }

    @PostMapping("/{id}/tramos")
    @ResponseStatus(HttpStatus.CREATED)
    public Tramo agregarTramo(@PathVariable Long id, @Valid @RequestBody TramoRequest request) {
        return carteraService.agregarTramo(id, request);
    }
}
