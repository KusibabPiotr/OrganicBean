package ka.piotr.organicbean.restaurant.repository;

import ka.piotr.organicbean.restaurant.model.domain.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen,Long> {
}
