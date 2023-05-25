package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Paso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasoRepository extends JpaRepository<Paso,Integer> {
}
