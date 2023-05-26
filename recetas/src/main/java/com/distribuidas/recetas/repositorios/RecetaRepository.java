package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    Receta findByNombreAndIdUsuario(String nombre, Integer idUsuario);

    List<Receta> findByNombre(String nombreReceta);

    List<Receta> findByIdTipo(Integer idTipo);

    @Query(value = "SELECT * FROM recetas \n" +
            "inner join utilizados u \n" +
            "    on recetas.idReceta = u.idReceta\n" +
            "WHERE U.idIngrediente = ?1", nativeQuery = true)
    List<Receta> recetasPorIngrediente(Integer idUsuario);

    @Query(value = "SELECT * FROM recetas r\n" +
            "    left join utilizados u\n" +
            "        on r.idReceta = u.idReceta\n" +
            "    where idIngrediente = ?1", nativeQuery = true)
    List<Receta> recetasSinIngredientes(Integer idIngrediente);

    List<Receta> findByIdUsuario(Integer idUsuario);
}
