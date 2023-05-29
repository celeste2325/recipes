package com.distribuidas.recetas.servicios.implementaciones;

import com.distribuidas.recetas.modelo.entities.Credencial;
import com.distribuidas.recetas.repositorios.CredencialRepository;
import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CredencialServiceImpl implements CredencialService {

    CredencialRepository credencialRepository;

    @Autowired
    CredencialServiceImpl(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Credencial> findAll() {
        return credencialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Credencial> findById(int id) {
        return credencialRepository.findById(id);
    }

    public Optional<Credencial> findByidUsuario(int idusuario) {
        return credencialRepository.findByidUsuario(idusuario);
    }

    @Transactional
    public void save(Credencial credencial) {
        credencialRepository.save(credencial);
    }

    @Transactional
    public void deleteById(int id) {
        credencialRepository.deleteById(id);

    }

    @Transactional
    public void deleteByidUsuario(int id) {
        Optional<Credencial> cred = findById(id);
        credencialRepository.deleteById(cred.get().getId());

    }
}
