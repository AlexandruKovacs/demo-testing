package com.titulizacion.poc.controller;

import com.titulizacion.poc.dto.ResumenPlataformaResponse;
import com.titulizacion.poc.service.CarteraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final CarteraService carteraService;

    public DashboardController(CarteraService carteraService) {
        this.carteraService = carteraService;
    }

    @GetMapping("/resumen")
    public ResumenPlataformaResponse resumen() {
        return carteraService.resumen();
    }
}
