package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
