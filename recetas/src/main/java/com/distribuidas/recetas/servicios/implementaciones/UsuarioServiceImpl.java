package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.excepciones.NoExisteUnUsuarioParaElIdIngresadoException;
import com.distribuidas.recetas.modelo.entities.Usuario;
import com.distribuidas.recetas.repositorios.UsuarioRepository;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    public List<Usuario> devolverUsuariosPorBusquedaParcialNombre(String nombreParcialUsuario) {
        if (!Objects.equals(nombreParcialUsuario, "")) {
            return this.usuarioRepository.findByNombreLikeIgnoreCase("%" + nombreParcialUsuario + "%");
        }
        return null;
    }

    @Override
    public String cargarUrlAvatar(Integer idUsuario, String url) throws NoExisteUnUsuarioParaElIdIngresadoException {
        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            usuario.get().setAvatar(url);
            this.usuarioRepository.save(usuario.get());
            return  usuario.get().getAvatar();
        } throw new NoExisteUnUsuarioParaElIdIngresadoException("No existe un usuario asociado al id ingresado");
    }

    @Override
    public Optional<Usuario> findByMail(String email) {
        return usuarioRepository.findByMail(email);

    }

}
