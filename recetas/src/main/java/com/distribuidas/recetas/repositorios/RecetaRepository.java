package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Receta;
import com.distribuidas.recetas.modelo.entities.RecetaAutorizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    Receta findByNombreAndIdUsuario(String nombre, Integer idUsuario);

    List<Receta> findByNombre(String nombreReceta);

    List<Receta> findByIdTipo(Integer idTipo);

    @Query(value = "SELECT ra FROM RecetaAutorizada ra inner join Utilizado u on ra.idReceta = u.idReceta WHERE u.idIngrediente = ?1")
    List<RecetaAutorizada> recetasPorIngrediente(Integer idIngrediente);

    @Query(value = "SELECT ra FROM RecetaAutorizada ra  inner join Utilizado u on ra.idReceta = u.idReceta where u.idIngrediente <> ?1")
    List<RecetaAutorizada> recetasSinIngredientes(Integer idIngrediente);

    List<Receta> findByIdUsuario(Integer idUsuario);

    List<Receta> findByNombreLikeIgnoreCase(String s);

    @Query(value = "SELECT ra FROM RecetaAutorizada ra  inner join Usuario u on ra.idUsuario = u.idUsuario where ra.nombre = ?1 order by u.nombre")
    List<RecetaAutorizada> findByNombreOrderByNombreUser(String nombreReceta);

    @Query(value = "SELECT top 3 ra.* FROM RecetasAutorizadas ra inner join usuarios u on r.idUsuario = u.idUsuario inner join fechasReceta f on r.idReceta = f.idReceta where r.idUsuario = ?1 order by f.fechaCreacion desc ", nativeQuery = true)
    List<RecetaAutorizada> recetasPorUsuarioOrdenadasPorFecha(Integer idUsuario);

    /*@Query(value = "SELECT r FROM Receta r inner join FechaReceta f on r.idReceta = f.idReceta inner join EstadoAutorizacion e on r.idReceta = e.idEntidad where r.nombre = ?1 and (e.tipoEstado = 'Autorizado' and e.tipoEntidad = 'Receta') order by f.fechaCreacion")
    List<Receta> findByNombreOrderByAntiguedad(String nombreReceta);*/

    @Query(value = "SELECT ra FROM RecetaAutorizada ra inner join FechaReceta f on ra.idReceta = f.idReceta where ra.nombre = ?1 order by f.fechaCreacion desc ")
    List<RecetaAutorizada> findByNombreOrderByAntiguedad(String nombreReceta);



}
