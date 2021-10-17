package ka.piotr.organicbean.product.controller.specification;

import ka.piotr.organicbean.product.model.domain.Allergen;
import ka.piotr.organicbean.product.model.domain.Dish;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class DishSpecification implements Specification<Dish> {

    private List<SearchCriteria> searchCriteriaList;

    public DishSpecification() {
        this.searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria){
        searchCriteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(
                        builder.greaterThan(
                                root.get(criteria.getKey()),
                                criteria.getValue().toString())
                );
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(
                        builder.lessThan(
                                root.get(criteria.getKey()),
                                criteria.getValue().toString())
                );
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(
                        builder.greaterThanOrEqualTo(
                                root.get(criteria.getKey()),
                                criteria.getValue().toString()
                        )
                );
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(
                        builder.lessThanOrEqualTo(
                                root.get(criteria.getKey()),
                                criteria.getValue().toString()
                        ));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(
                        builder.notEqual(root.get(
                                criteria.getKey()), criteria.getValue())
                );
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(
                        builder.equal(root.get(
                                criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get(criteria.getKey()))
                                , "%" + criteria.getValue().toString().toLowerCase() + "%")
                );
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get(criteria.getKey())),
                                criteria.getValue().toString().toLowerCase() + "%")
                );
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add
                        (builder.like(
                                builder.lower(root.get(criteria.getKey())),
                                "%" + criteria.getValue().toString().toLowerCase())
                        );
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(
                        builder.in(
                                root.get(criteria.getKey())
                        ).value(criteria.getValue())
                );
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(
                        builder.not(
                                root.get(criteria.getKey())
                        ).in(criteria.getValue()));
            } else {

            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}