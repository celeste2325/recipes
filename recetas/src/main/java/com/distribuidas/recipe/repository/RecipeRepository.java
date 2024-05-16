package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findByNombreAndIdUsuario(String nombre, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.idUsuario = usu.idUsuario inner join DateOfRecipe fr on r.idReceta = fr.idReceta inner join IngredientUsed u on r.idReceta = u.idReceta inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE u.idIngrediente <> ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2 )) order by fr.fechaCreacion desc")
    List<Recipe> getRecipesWithoutIngredientsOrderByDate(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.idUsuario = usu.idUsuario inner join IngredientUsed u on r.idReceta = u.idReceta inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE u.idIngrediente <> ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2 )) order by usu.nombre asc")
    List<Recipe> recetasSinIngredientesOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.idUsuario = usu.idUsuario inner join IngredientUsed u on r.idReceta = u.idReceta inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE u.idIngrediente <> ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2 )) order by r.nombre asc")
    List<Recipe> recetasSinIngredientesOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User u on r.idUsuario = u.idUsuario inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE r.nombre like ?1% and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by r.nombre")
    List<Recipe> findByNombreLikeIgnoreCase(String s, Integer idUsuario);

    @Query(value = "SELECT r FROM Recipe r inner join User u on r.idUsuario = u.idUsuario inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE r.nombre = ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by u.nombre")
    List<Recipe> findByNombreOrderByNombreUser(String nombreReceta, Integer idUsuario);

    @Query(value = "SELECT top 3 r.* FROM recetas r inner join fechasReceta f on r.idReceta = f.idReceta inner join estados_autorizaciones es on es.id_entidad = r.idReceta where ((es.tipo_estado='Autorizado' and es.tipo_entidad='Receta') OR (es.tipo_estado='No autorizado' and es.tipo_entidad='Receta' and r.idUsuario = ?1)) order by f.fechaCreacion desc ", nativeQuery = true)
    List<Recipe> recetasPorUsuarioOrdenadasPorFecha(Integer idUsuario);

    @Query(value = "SELECT r FROM Recipe r inner join DateOfRecipe f on r.idReceta = f.idReceta inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE r.nombre = ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by f.fechaCreacion desc")
    List<Recipe> findByNombreOrderByAntiguedad(String nombreReceta, Integer idUsuario);

    @Query("SELECT DISTINCT r FROM Recipe r inner join User usu on r.idUsuario = usu.idUsuario inner join IngredientUsed u on r.idReceta = u.idReceta inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE (r.nombre = ?1 OR r.idTipo = ?2 OR u.idIngrediente = ?3 OR usu.idUsuario = ?4) and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?5)) order by r.nombre")
    List<Recipe> recetasByParamQuery(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio);

    @Query(value = "SELECT DISTINCT r FROM Recipe r inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE (es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?1)")
    List<Recipe> devolverTodasLasrecetasHabilitadas(Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User u on r.idUsuario = u.idUsuario inner join AuthorizationStatus es on es.idEntidad = r.idReceta WHERE r.idReceta = ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by r.nombre")
    Optional<Recipe> devuelveRecetasPorId(Integer id, Integer idUsuario);

}
