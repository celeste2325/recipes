package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.EstadoAutorizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoAutorizacionRepository extends JpaRepository<EstadoAutorizacion, Integer> {
}
