package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.AllergenType;
import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.repository.AllergenRepository;
import ka.piotr.organicbean.product.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;
    private static final Long NONE = 1L;
    private static final Long GLUTENFREE = 2L;
    private static final Long VEGAN = 3L;
    private static final Long VEGETARIAN = 4L;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDish(final Long id){
        return dishRepository.findById(id);
    }

    public Dish createDish(final Dish dish){
        return dishRepository.save(dish);
    }

    public void deleteDish(final Long id){
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllByParams(String params) {
        if (params == null ) {
            return getAllDishes();
        }
        Set<String> split = Set.of(params.split(","));

        if (split.contains("vegan") && split.contains("vegetarian") && split.contains("glutenFree")) {
            return dishRepository.findAll().stream()
                    .filter(e->e.getAllergens()
                                    .containsAll(List.of(allergenRepository.getById(GLUTENFREE),
                                                        allergenRepository.getById(VEGETARIAN),
                                                        allergenRepository.getById(VEGAN))))
                    .collect(Collectors.toList());
        }  else if (split.contains("glutenFree") && split.contains("vegetarian")) {
            return dishRepository.findAll().stream()
                    .filter(e->e.getAllergens()
                                    .containsAll(List.of(allergenRepository.getById(VEGETARIAN),
                                                        allergenRepository.getById(GLUTENFREE))))
                    .collect(Collectors.toList());
        } else if (split.contains("glutenFree") && split.contains("vegan")) {
            return dishRepository.findAll().stream()
                    .filter(e->e.getAllergens()
                                    .containsAll(List.of(allergenRepository.getById(VEGAN),
                                                        allergenRepository.getById(GLUTENFREE))))
                    .collect(Collectors.toList());
        } else if (split.contains("vegan") && split.contains("vegetarian")) {
            return dishRepository.findAll().stream()
                    .filter(e->e.getAllergens()
                                    .containsAll(List.of(allergenRepository.getById(VEGAN),
                                                        allergenRepository.getById(VEGETARIAN))))
                    .collect(Collectors.toList());
        } else if (split.contains("vegan")) {
            return dishRepository.findAll().stream()
                    .filter(e->e.getAllergens()
                            .contains(allergenRepository.getById(VEGAN)))
                    .collect(Collectors.toList());
        } else if ((split.contains("vegetarian"))) {
            return dishRepository.findAll().stream()
                    .filter(e->e.getAllergens()
                            .contains(allergenRepository.getById(VEGETARIAN)))
                    .collect(Collectors.toList());
        } else if ((split.contains("glutenFree"))) {
            return dishRepository.findAll().stream()
                    .filter(e -> e.getAllergens()
                            .contains(allergenRepository.getById(GLUTENFREE)))
                    .collect(Collectors.toList());
        } else if ((split.contains("none"))) {
            return dishRepository.findAll().stream()
                    .filter(e -> e.getAllergens()
                            .contains(allergenRepository.getById(NONE)))
                    .collect(Collectors.toList());
        }
        throw new UnsupportedOperationException("BlaBla");

    }
    }

