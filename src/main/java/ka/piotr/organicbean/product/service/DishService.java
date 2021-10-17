package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.controller.specification.DishSpecification;
import ka.piotr.organicbean.product.controller.specification.SearchCriteria;
import ka.piotr.organicbean.product.controller.specification.SearchOperation;
import ka.piotr.organicbean.product.exceptions.NoSuchAllergenTypeException;
import ka.piotr.organicbean.product.model.AllergenType;
import ka.piotr.organicbean.product.model.domain.Allergen;
import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.repository.AllergenRepository;
import ka.piotr.organicbean.product.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;

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

    public List<Dish> getAllByParams(String params) throws NoSuchAllergenTypeException {
        if (params == null){
            return dishRepository.findAll();
        }
        List<Allergen> all = allergenRepository.findAll();
        Set<String> split = Set.of(params.split(","));
        Set<Allergen> allergens = new HashSet<>();

        for (String s : split) {
            for (Allergen allergen : all) {
                if (AllergenType.getTypeFromDescription(s).equals(allergen.getAllergenType())){
                    allergens.add(allergen);
                }
            }
        }
        return dishRepository.findByAllergenSet(allergens,Integer.toUnsignedLong(allergens.size()));

    }

    public List<Dish> getAllByParams2(String params, String name,
                                      Integer kcal, BigDecimal price)
            throws NoSuchAllergenTypeException {
        if (params == null && name == null && kcal == null && price == null){
            return dishRepository.findAll();
        }
        DishSpecification dishSpecification = new DishSpecification();

        List<Allergen> all = allergenRepository.findAll();
        if (params != null){
            Set<String> split = Set.of(params.split(","));
            Set<Allergen> allergens = new HashSet<>();

            for (String s : split) {
                for (Allergen allergen : all) {
                    if (AllergenType.getTypeFromDescription(s).equals(allergen.getAllergenType())){
                        allergens.add(allergen);
                    }
                }
            }
//            dishSpecification.addSearchCriteria(new SearchCriteria("allergens",allergens, SearchOperation.IN));
        }

        if (name != null)
            dishSpecification.addSearchCriteria(new SearchCriteria("name",name,SearchOperation.MATCH));
        if (kcal != null)
            dishSpecification.addSearchCriteria(new SearchCriteria("kcal",kcal,SearchOperation.GREATER_THAN));
        if (price != null)
            dishSpecification.addSearchCriteria(new SearchCriteria("price",price,SearchOperation.GREATER_THAN));

        return dishRepository.findAll(dishSpecification);
    }

}

