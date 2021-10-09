package ka.piotr.organicbean.product.repository;

import ka.piotr.organicbean.product.model.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DishRepository extends JpaRepository<Dish,Long> {

    List<Dish> findAllByGlutenFree(boolean isGlutenFree);
    List<Dish> findAllByVegan(boolean isVegan);
    List<Dish> findAllByVegetarian(boolean isVegetarian);
    List<Dish> findAllByGlutenFreeAndVegetarian(boolean isGlutenFree, boolean isVegetarian);
    List<Dish> findAllByGlutenFreeAndVegan(boolean isGlutenFree, boolean isVegan);
    List<Dish> findAllByVeganAndVegetarian(boolean isVegan, boolean isVegetarian);
    List<Dish> findAllByGlutenFreeAndVeganAndVegetarian(boolean isGlutenFree, boolean isVegan, boolean isVegetarian);

}
