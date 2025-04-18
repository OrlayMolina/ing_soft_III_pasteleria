package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findByStatus(Status status);

    List<RecipeEntity> findByNameContainingIgnoreCase(String name);

    @Query("SELECT r FROM RecipeEntity r JOIN r.recipeSupplies rs WHERE rs.supply.id = :supplyId")
    List<RecipeEntity> findBySupplyId(@Param("supplyId") Long supplyId);
}