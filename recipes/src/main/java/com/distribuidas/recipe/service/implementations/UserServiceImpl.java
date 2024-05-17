package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.exception.UserDoesNotExistException;
import com.distribuidas.recipe.model.entities.User;
import com.distribuidas.recipe.repository.UserRepository;
import com.distribuidas.recipe.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(int ID) {
        return userRepository.findById(ID);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteById(int ID) {
        userRepository.deleteById(ID);

    }

    @Override
    public List<User> getUsersByPartialName(String partialNameUser) {
        if (!Objects.equals(partialNameUser, "")) {
            return this.userRepository.findByNameLikeIgnoreCase(partialNameUser + "%");
        }
        return null;
    }



    @Override
    public List<String> getAliasOption(String nickname) {
    	List<String> alternativas = new ArrayList<>();
    	int i = 1;
    	while (alternativas.size() < 3) {
    		String alternativa = nickname + i;
            Optional<User> usuario = this.userRepository.findByNickname(alternativa);
            if(!usuario.isPresent()) {
            	alternativas.add(alternativa);
            }
        i++;
    	}
    	return alternativas;
    }

    @Override
    public String saveUrlAvatar(Integer userID, String url) throws UserDoesNotExistException {
        Optional<User> user = this.userRepository.findById(userID);
        if (user.isPresent()) {
            user.get().setAvatar(url);
            this.userRepository.save(user.get());
            return  user.get().getAvatar();
        } throw new UserDoesNotExistException("No user for ID introduced");
    }

    @Override
    public Optional<User> findByMail(String email) {
        return userRepository.findByMail(email);

    }

}
