package ka.piotr.organicbean.product.repository;

import ka.piotr.organicbean.product.model.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DishRepository extends JpaRepository<Dish,Long> {
}
