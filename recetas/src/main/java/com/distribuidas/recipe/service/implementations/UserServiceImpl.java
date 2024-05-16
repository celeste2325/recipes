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

    UserRepository usuarioRepository;

    @Autowired
    UserServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname);
    }

    @Transactional
    public void save(User usuario) {
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteById(int id) {
        usuarioRepository.deleteById(id);

    }

    @Override
    public List<User> devolverUsuariosPorBusquedaParcialNombre(String nombreParcialUsuario) {
        if (!Objects.equals(nombreParcialUsuario, "")) {
            return this.usuarioRepository.findByNombreLikeIgnoreCase(nombreParcialUsuario + "%");
        }
        return null;
    }



    @Override
    public List<String> opcionesAlias(String alias) {
    	List<String> alternativas = new ArrayList<>();
    	int i = 1;
    	while (alternativas.size() < 3) {
    		String alternativa = alias + i;
            Optional<User> usuario = this.usuarioRepository.findByNickname(alternativa);
            if(!usuario.isPresent()) {
            	alternativas.add(alternativa);
            }
        i++;
    	}
    	return alternativas;
    }

    @Override
    public String cargarUrlAvatar(Integer idUsuario, String url) throws UserDoesNotExistException {
        Optional<User> usuario = this.usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            usuario.get().setAvatar(url);
            this.usuarioRepository.save(usuario.get());
            return  usuario.get().getAvatar();
        } throw new UserDoesNotExistException("No existe un usuario asociado al id ingresado");
    }

    @Override
    public Optional<User> findByMail(String email) {
        return usuarioRepository.findByMail(email);

    }

}
