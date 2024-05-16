package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNickname(String nickname);

    Optional<Usuario> findByMail(String email);

    List<Usuario> findByNombreLikeIgnoreCase(String s);
}
