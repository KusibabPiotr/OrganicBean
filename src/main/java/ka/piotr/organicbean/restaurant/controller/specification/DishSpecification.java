package ka.piotr.organicbean.restaurant.controller.specification;

import ka.piotr.organicbean.restaurant.model.domain.Dish;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class DishSpecification implements Specification<Dish> {

    private List<SearchCriteria> searchCriteriaList;

    public DishSpecification() {
        this.searchCriteriaList = new ArrayList<>();
    }

    public void addSearchCriteria(SearchCriteria criteria) {
        searchCriteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList) {

            if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get(criteria.getKey()))
                                , "%" + criteria.getValue().toString().toLowerCase() + "%")
                );
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(
                        builder.in(
                                root.get(criteria.getKey()))
                                .value(criteria.getValue())
                );
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(
                        builder.not(
                                root.get(criteria.getKey())
                        ).in(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.BETWEEN)) {
                predicates.add(
                        builder.between(
                                root.get(criteria.getKey()),
                                (int)criteria.getValue(),
                                (int)criteria.getValue2()
                        )
                );
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
