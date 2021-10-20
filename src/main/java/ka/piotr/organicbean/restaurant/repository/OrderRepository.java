package ka.piotr.organicbean.restaurant.repository;

import ka.piotr.organicbean.restaurant.model.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select distinct o from Order o inner join fetch o.dishes d " +
            "inner join fetch d.allergens a")
    List<Order> findAllOrders();

    @Query("select o from Order o inner join fetch o.dishes d " +
            "inner join fetch d.allergens a where o.id = :id")
    Optional<Order> findOrderById(@Param("id") Long id);
}
