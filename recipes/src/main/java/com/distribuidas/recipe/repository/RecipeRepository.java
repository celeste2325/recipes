package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findByNameAndUserID(String name, Integer userID);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join DateOfRecipe fr on r.recipeID = fr.recipeID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE u.ingredientID <> ?1 and ((es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='No autorizado' and es.entityType='Receta' and r.userID = ?2 )) order by fr.dateCreation desc")
    List<Recipe> getRecipesWithoutIngredientsOrderByDate(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE u.ingredientID <> ?1 and ((es.statusType='Authorized' and es.entityType='Receta') OR (es.statusType='No autorizado' and es.entityType='Receta' and r.userID = ?2 )) order by usu.name asc")
    List<Recipe> recetasSinIngredientesOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE u.ingredientID <> ?1 and ((es.statusType='Authorized' and es.entityType='Receta') OR (es.statusType='No autorizado' and es.entityType='Receta' and r.userID = ?2 )) order by r.name asc")
    List<Recipe> recetasSinIngredientesOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User u on r.userID = u.userID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.name like ?1% and ((es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='Not authorized' and es.entityType='Receta' and r.userID = ?2)) order by r.name")
    List<Recipe> findByNombreLikeIgnoreCase(String s, Integer idUsuario);

    @Query(value = "SELECT r FROM Recipe r inner join User u on r.userID = u.userID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.name = ?1 and ((es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='Not authorized' and es.entityType='Receta' and r.userID = ?2)) order by u.name")
    List<Recipe> findByNombreOrderByNombreUser(String nombreReceta, Integer idUsuario);

    @Query(value = "SELECT top 3 r.* FROM recetas r inner join fechasReceta f on r.idReceta = f.idReceta inner join estados_autorizaciones es on es.id_entidad = r.idReceta where ((es.tipo_estado='Autorizado' and es.tipo_entidad='Receta') OR (es.tipo_estado='Not authorized' and es.tipo_entidad='Receta' and r.idUsuario = ?1)) order by f.fechaCreacion desc ", nativeQuery = true)
    List<Recipe> recetasPorUsuarioOrdenadasPorFecha(Integer idUsuario);

    @Query(value = "SELECT r FROM Recipe r inner join DateOfRecipe f on r.recipeID = f.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.name = ?1 and ((es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='Not authorized' and es.entityType='Receta' and r.userID = ?2)) order by f.dateCreation desc")
    List<Recipe> findByNombreOrderByAntiguedad(String nombreReceta, Integer idUsuario);

    @Query("SELECT DISTINCT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE (r.name = ?1 OR r.categoryID = ?2 OR u.ingredientID = ?3 OR usu.userID = ?4) and ((es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='No autorizado' and es.entityType='Receta' and r.userID = ?5)) order by r.name")
    List<Recipe> recetasByParamQuery(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio);

    @Query(value = "SELECT DISTINCT r FROM Recipe r inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE (es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='No autorizado' and es.entityType='Receta' and r.userID = ?1)")
    List<Recipe> devolverTodasLasrecetasHabilitadas(Integer idUsuario);
    @Query(value = "SELECT r FROM Recipe r inner join User u on r.userID = u.userID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.recipeID = ?1 and ((es.statusType='Autorizado' and es.entityType='Receta') OR (es.statusType='Not authorized' and es.entityType='Receta' and r.userID = ?2)) order by r.name")
    Optional<Recipe> devuelveRecetasPorId(Integer id, Integer idUsuario);

}
