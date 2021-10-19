package ka.piotr.organicbean.restaurant.repository;

import ka.piotr.organicbean.restaurant.model.domain.Allergen;
import ka.piotr.organicbean.restaurant.model.domain.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface DishRepository extends JpaRepository<Dish,Long>, JpaSpecificationExecutor<Dish> {

    @Query("select d from Dish d inner join d.allergens a " +
            "where a in :set " +
            "group by d having count(a) = :listSize")
    List<Dish> findByAllergenSet(@Param("set") Set<Allergen> set, @Param("listSize") long listSize);

    @Query("select distinct d from Dish d inner join fetch d.allergens a")
    List<Dish> findAllDishes();


    @Query("select d from Dish d inner join fetch d.allergens a " +
            "where d.id = :id")
    Optional<Dish> findDishId(@Param("id") Long id);
}
