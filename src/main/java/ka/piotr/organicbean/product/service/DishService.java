package ka.piotr.organicbean.product.service;

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
        if (params == null) {
            return getAllDishes();
        }
        Set<String> split = Set.of(params.split(","));

        if (split.contains("vegan") && split.contains("vegetarian") && split.contains("glutenFree")) {
            return getAllGlutenFreeAndVeganAndVegetarian();
        }  else if (split.contains("glutenFree") && split.contains("vegetarian")) {
            return getAllGlutenFreeAndVegetarian();
        } else if (split.contains("glutenFree") && split.contains("vegan")) {
            return getAllGlutenFreeAndVegan();
        } else if (split.contains("vegan") && split.contains("vegetarian")) {
            return getAllVeganAndVegetarian();
        } else if (split.contains("vegan") && split.size() == 1) {
            return getAllVegan();
        } else if ((split.contains("vegetarian") && split.size() == 1)) {
            return getAllVegetarian();
        } else return getAllGlutenFree();
    }

    private List<Dish> getAllGlutenFree() {
        return dishRepository.findAllByGlutenFree(true);
    }

    private List<Dish> getAllVegan() {
        return dishRepository.findAllByVegan(true);
    }

    private List<Dish> getAllVegetarian() {
        return dishRepository.findAllByVegetarian(true);
    }

    private List<Dish> getAllGlutenFreeAndVegetarian() {
        return dishRepository.findAllByGlutenFreeAndVegetarian(true,true);
    }

    private List<Dish> getAllGlutenFreeAndVegan() {
        return dishRepository.findAllByGlutenFreeAndVegan(true,true);
    }

    private List<Dish> getAllVeganAndVegetarian() {
        return dishRepository.findAllByVeganAndVegetarian(true,true);
    }

    private List<Dish> getAllGlutenFreeAndVeganAndVegetarian() {
        return dishRepository.findAllByGlutenFreeAndVeganAndVegetarian(true,true,true);
    }
    }

