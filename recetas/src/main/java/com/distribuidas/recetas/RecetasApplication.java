package com.distribuidas.recetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
