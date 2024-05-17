package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.exception.UserDoesNotExistException;
import com.distribuidas.recipe.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(int ID);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByMail(String email);

    void save(User user);

    void deleteById(int ID);

    List<User> getUsersByPartialName(String partialNameUser);

    List<String> getAliasOption(String nickname);

    String saveUrlAvatar(Integer userID, String url) throws UserDoesNotExistException;
}
