package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findByNameAndUserID(String name, Integer userID);

    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join DateOfRecipe fr on r.recipeID = fr.recipeID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE u.ingredientID <> ?1 and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='No Authorized' and es.entityType='Recipe' and r.userID = ?2 )) order by fr.dateCreation desc")
    List<Recipe> getRecipesWithoutIngredientsOrderByDate(Integer ingredientID, Integer userID);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE u.ingredientID <> ?1 and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='No Authorized' and es.entityType='Recipe' and r.userID = ?2 )) order by usu.name asc")
    List<Recipe> recipesWithoutIngredientOrderByUserName(Integer ingredientID, Integer userID);
    @Query(value = "SELECT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE u.ingredientID <> ?1 and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='No Authorized' and es.entityType='Recipe' and r.userID = ?2 )) order by r.name asc")
    List<Recipe> recipesWithoutIngredientOrderByRecipeName(Integer ingredientID, Integer userID);
    @Query(value = "SELECT r FROM Recipe r inner join User u on r.userID = u.userID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.name like ?1% and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='Not authorized' and es.entityType='Recipe' and r.userID = ?2)) order by r.name")
    List<Recipe> findByRecipeNameLikeIgnoreCase(String s, Integer userID);

    @Query(value = "SELECT r FROM Recipe r inner join User u on r.userID = u.userID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.name = ?1 and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='Not authorized' and es.entityType='Recipe' and r.userID = ?2)) order by u.name")
    List<Recipe> findByRecipeNameOrderByUserName(String recipeName, Integer userID);

    @Query(value = "SELECT top 3 r.* FROM Recipes r inner join fechasRecipe f on r.idRecipe = f.idRecipe inner join estados_autorizaciones es on es.id_entidad = r.idRecipe where ((es.tipo_estado='Authorized' and es.tipo_entidad='Recipe') OR (es.tipo_estado='Not authorized' and es.tipo_entidad='Recipe' and r.idUsuario = ?1)) order by f.fechaCreacion desc ", nativeQuery = true)
    List<Recipe> findByUserIdOrderByDate(Integer userID);

    @Query(value = "SELECT r FROM Recipe r inner join DateOfRecipe f on r.recipeID = f.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.name = ?1 and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='Not authorized' and es.entityType='Recipe' and r.userID = ?2)) order by f.dateCreation desc")
    List<Recipe> findByRecipeNameOrderByAntiquity(String recipeName, Integer userID);

    @Query("SELECT DISTINCT r FROM Recipe r inner join User usu on r.userID = usu.userID inner join IngredientUsed u on r.recipeID = u.recipeID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE (r.name = ?1 OR r.categoryID = ?2 OR u.ingredientID = ?3 OR usu.userID = ?4) and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='No Authorized' and es.entityType='Recipe' and r.userID = ?5)) order by r.name")
    List<Recipe> recipesByParamQuery(String recipeName, Integer categoryID, Integer ingredientID, Integer userID, Integer mandatoryUserID);

    @Query(value = "SELECT DISTINCT r FROM Recipe r inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE (es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='Not authorized' and es.entityType='Recipe' and r.userID = ?1)")
    List<Recipe> getRecipes(Integer userID);
    @Query(value = "SELECT r FROM Recipe r inner join User u on r.userID = u.userID inner join AuthorizationStatus es on es.entityID = r.recipeID WHERE r.recipeID = ?1 and ((es.statusType='Authorized' and es.entityType='Recipe') OR (es.statusType='Not authorized' and es.entityType='Recipe' and r.userID = ?2)) order by r.name")
    Optional<Recipe> getRecipeByID(Integer id, Integer userID);

}
