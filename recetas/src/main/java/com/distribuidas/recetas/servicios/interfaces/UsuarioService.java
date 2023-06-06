package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.excepciones.NoExisteUnUsuarioParaElIdIngresadoException;
import com.distribuidas.recetas.modelo.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Iterable<Usuario> findAll();

    Optional<Usuario> findById(int id);

    Optional<Usuario> findByNickname(String nickname);

    Optional<Usuario> findByMail(String email);

    void save(Usuario usuario);

    void deleteById(int id);

    List<Usuario> devolverUsuariosPorBusquedaParcialNombre(String nombreParcialUsuario);

    String cargarUrlAvatar(Integer idUsuario, String url) throws NoExisteUnUsuarioParaElIdIngresadoException;
}
