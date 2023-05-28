package com.distribuidas.recetas;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.distribuidas.recetas.modelo.Usuario;
import com.distribuidas.recetas.repositorios.UsuarioRepository;

@SpringBootApplication
public class RecetasApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecetasApplication.class, args);
    }
    
    /*
    @Bean
        CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository) {
            return args-> {
                Optional<Usuario> nuevo = usuarioRepository.findById(5);
                System.out.println(nuevo.get().getNombre());

            };
        }*/
    

}
