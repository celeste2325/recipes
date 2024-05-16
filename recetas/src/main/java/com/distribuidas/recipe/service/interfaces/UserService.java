package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.exception.UserDoesNotExistException;
import com.distribuidas.recipe.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(int id);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByMail(String email);

    void save(User usuario);

    void deleteById(int id);

    List<User> devolverUsuariosPorBusquedaParcialNombre(String nombreParcialUsuario);

    List<String> opcionesAlias(String alias);

    String cargarUrlAvatar(Integer idUsuario, String url) throws UserDoesNotExistException;
}
