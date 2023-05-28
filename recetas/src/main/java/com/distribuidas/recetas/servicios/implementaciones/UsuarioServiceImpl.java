package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.Usuario;
import com.distribuidas.recetas.repositorios.UsuarioRepository;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByNickname(String nickname) {
    	return usuarioRepository.findByNickname(nickname);
    }

    @Transactional
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteById(int id) {
        usuarioRepository.deleteById(id);

    }

	@Override
	public Optional<Usuario> findByMail(String email) {
        return usuarioRepository.findByMail(email);

	}


}
