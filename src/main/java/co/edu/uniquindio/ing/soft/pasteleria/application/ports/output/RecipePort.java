package co.edu.uniquindio.ing.soft.pasteleria.application.ports.output;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Recipe;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface RecipePort {
    Recipe saveRecipe(Recipe Recipe) throws DomainException;

//    Recipe updateRecipe(Recipe Recipe) throws DomainException;
//
//    Optional<Recipe> findRecipeById(Long id);
//
//    Optional<RecipeResponse> findRecipeByIdAllData(Long id);
//
//    void deleteRecipeById(Long id);
//
//    List<RecipeResponse> findAllRecipes();
//
//    boolean existsRecipeById(Long id);
//
//    Page<Recipe> findRecipesWithPagination(int page, int size);
//
//    Page<RecipeResponse> findRecipesWithPaginationAndSorting(int page, int size, String sortField, String sortDirection, String searchTerm);
}
