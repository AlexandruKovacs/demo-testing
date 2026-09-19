package com.titulizacion.poc.repository;

import com.titulizacion.poc.model.Activo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivoRepository extends JpaRepository<Activo, Long> {
}
