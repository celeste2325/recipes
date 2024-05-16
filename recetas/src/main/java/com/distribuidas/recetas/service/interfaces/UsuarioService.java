package com.distribuidas.recetas.service.interfaces;

import com.distribuidas.recetas.exception.NoExisteUnUsuarioParaElIdIngresadoException;
import com.distribuidas.recetas.model.entities.Usuario;

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

    List<String> opcionesAlias(String alias);

    String cargarUrlAvatar(Integer idUsuario, String url) throws NoExisteUnUsuarioParaElIdIngresadoException;
}
