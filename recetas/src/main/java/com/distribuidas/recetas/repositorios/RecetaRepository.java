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

    List<Receta> findByNombreLikeIgnoreCase(String s);

    @Query(value = "SELECT r FROM Receta r  inner join Usuario u on r.idUsuario = u.idUsuario where r.nombre = ?1 order by u.nombre")
    List<Receta> findByNombreOrderByNombreUser(String nombreReceta);

    @Query(value = "SELECT top 3 r.* FROM recetas r inner join usuarios u on r.idUsuario = u.idUsuario inner join fechasReceta f on r.idReceta = f.idReceta where r.idUsuario = ?1 order by f.fechaCreacion desc ", nativeQuery = true)
    List<Receta> recetasPorUsuarioOrdenadasPorFecha(Integer idUsuario);

    @Query(value = "SELECT r FROM Receta r  inner join FechaReceta f on r.idReceta = f.idReceta where r.nombre = ?1 order by f.fechaCreacion")
    List<Receta> findByNombreOrderByAntiguedad(String nombreReceta);
}
