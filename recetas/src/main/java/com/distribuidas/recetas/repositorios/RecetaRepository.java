package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    Receta findByNombreAndIdUsuario(String nombre, Integer idUsuario);

    List<Receta> findByNombre(String nombreReceta);

    List<Receta> findByIdTipo(Integer idTipo);

    @Query(value = "SELECT r FROM Receta r inner join Utilizado u on r.idReceta = u.idReceta WHERE u.idIngrediente = ?1")
    List<Receta> recetasPorIngrediente(Integer idIngrediente);

    @Query(value = "SELECT r FROM Receta r  inner join Utilizado u on r.idReceta = u.idReceta where u.idIngrediente <> ?1")
    List<Receta> recetasSinIngredientes(Integer idIngrediente);

    List<Receta> findByIdUsuario(Integer idUsuario);
}
