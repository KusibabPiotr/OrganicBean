package ka.piotr.organicbean.restaurant.service;

import ka.piotr.organicbean.restaurant.controller.specification.DishSpecification;
import ka.piotr.organicbean.restaurant.controller.specification.SearchCriteria;
import ka.piotr.organicbean.restaurant.controller.specification.SearchOperation;
import ka.piotr.organicbean.restaurant.exceptions.DishNotFoundException;
import ka.piotr.organicbean.restaurant.exceptions.NoSuchAllergenTypeException;
import ka.piotr.organicbean.restaurant.model.AllergenType;
import ka.piotr.organicbean.restaurant.model.domain.Allergen;
import ka.piotr.organicbean.restaurant.model.domain.Dish;
import ka.piotr.organicbean.restaurant.repository.AllergenRepository;
import ka.piotr.organicbean.restaurant.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;

    public Optional<Dish> getDish(final Long id){
        return dishRepository.findDishId(id);
    }

    public Dish createDish(final Dish dish){
        return dishRepository.save(dish);
    }

    @Transactional
    public Dish updateDish(final Dish dish, final Long id)
            throws DishNotFoundException {
        Dish dishDb = dishRepository.findById(id)
                .orElseThrow(DishNotFoundException::new);
        dishDb.setName(dish.getName());
        dishDb.setDescription(dish.getDescription());
        dishDb.setPrice(dish.getPrice());
        dishDb.setKcal(dish.getKcal());
        return dishDb;
    }

    public void deleteDish(final Long id)
            throws IllegalArgumentException{
        dishRepository.deleteById(id);
    }

    private List<Dish> byAllergensFiltering(String params)
            throws NoSuchAllergenTypeException {
        List<Allergen> all = allergenRepository.findAll();
        Set<String> split = Set.of(params.split(","));
        Set<Allergen> allergens = new HashSet<>();

        for (String s : split) {
            for (Allergen allergen : all) {
                if (AllergenType.getTypeFromDescription(s)
                        .equals(allergen.getAllergenType())){
                    allergens.add(allergen);
                }
            }
        }
        return dishRepository.findByAllergenSet(
                allergens,Integer.toUnsignedLong(allergens.size()));

    }

    public List<Dish> getByParams(String params, String name,
                                      Integer minKcal, Integer maxKcal,
                                  Integer minPrice, Integer maxPrice)
            throws NoSuchAllergenTypeException {

        if (params == null && name == null && maxKcal == null && maxPrice == null){
            return dishRepository.findAllDishes();
        }

        DishSpecification dishSpecification = new DishSpecification();
        List<Dish> byAllergenSet = new ArrayList<>();
        List<Dish> byRestParameters = new ArrayList<>();

        if(params != null){
            byAllergenSet = byAllergensFiltering(params);
        }

        if (name != null || maxKcal != null || maxPrice != null){
            if (name != null)
                dishSpecification.addSearchCriteria(new SearchCriteria(
                        "name",name,SearchOperation.MATCH));
            if (maxKcal != null)
                dishSpecification.addSearchCriteria(new SearchCriteria(
                        "kcal",minKcal,maxKcal,SearchOperation.BETWEEN));
            if (maxPrice != null)
                dishSpecification.addSearchCriteria(new SearchCriteria(
                        "price",minPrice,maxPrice,SearchOperation.BETWEEN));

            byRestParameters = dishRepository.findAll(dishSpecification);
        }

        if (!byAllergenSet.isEmpty() && !byRestParameters.isEmpty()){
            return byAllergenSet.stream()
                    .filter(byRestParameters::contains)
                    .collect(Collectors.toList());

        } else if (!byAllergenSet.isEmpty()){
            return byAllergenSet;
        } else {
            return byRestParameters;
        }
    }

}

