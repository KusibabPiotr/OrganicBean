package ka.piotr.organicbean.product.controller.specification;

import ka.piotr.organicbean.product.model.domain.Dish;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DishSpecification implements Specification<Dish> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
