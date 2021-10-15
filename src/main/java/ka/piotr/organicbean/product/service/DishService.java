package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.AllergenType;
import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

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
        if (params == null || params.contains("none")) {
            return getAllDishes();
        }
        Set<String> split = Set.of(params.split(","));

        if (split.contains("vegan") && split.contains("vegetarian") && split.contains("glutenFree")) {
            return dishRepository.findAllByAllergenType(AllergenType.GLUTEN_FREE_VEGAN_VEGETARIAN);
        }  else if (split.contains("glutenFree") && split.contains("vegetarian")) {
            return dishRepository.findAllByAllergenType(AllergenType.GLUTEN_FREE_VEGETARIAN);
        } else if (split.contains("glutenFree") && split.contains("vegan")) {
            return dishRepository.findAllByAllergenType(AllergenType.GLUTEN_FREE_VEGAN);
        } else if (split.contains("vegan") && split.contains("vegetarian")) {
            return dishRepository.findAllByAllergenType(AllergenType.VEGAN_VEGETARIAN);
        } else if (split.contains("vegan") && split.size() == 1) {
            return dishRepository.findAllByAllergenType(AllergenType.VEGAN);
        } else if ((split.contains("vegetarian") && split.size() == 1)) {
            return dishRepository.findAllByAllergenType(AllergenType.VEGAN_VEGETARIAN);
        } else return dishRepository.findAllByAllergenType(AllergenType.GLUTEN_FREE);
    }
    }

