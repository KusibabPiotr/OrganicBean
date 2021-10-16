package ka.piotr.organicbean.product.repository;

import ka.piotr.organicbean.product.model.AllergenType;
import ka.piotr.organicbean.product.model.domain.Allergen;
import ka.piotr.organicbean.product.model.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface DishRepository extends JpaRepository<Dish,Long>, JpaSpecificationExecutor<Dish> {

    List<Dish> findAllByAllergensIsIn(Set <Allergen> allergenSet);
}
