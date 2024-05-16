package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNickname(String nickname);

    Optional<User> findByMail(String email);

    List<User> findByNombreLikeIgnoreCase(String s);
}
