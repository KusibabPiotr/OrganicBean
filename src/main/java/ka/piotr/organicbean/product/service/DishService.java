package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public List<Dish> getAllGlutenFree(boolean isGlutenFree) {
        return dishRepository.findAllByGlutenFree(isGlutenFree);
    }
    public List<Dish> getAllVegan(boolean isVegan) {
        return dishRepository.findAllByVegan(isVegan);
    }

    public List<Dish> getAllVegetarian(boolean isVegetarian) {
        return dishRepository.findAllByVegetarian(isVegetarian);
    }

    public Optional<Dish> getDish(final Long id){
        return dishRepository.findById(id);
    }

    public Dish saveDish(final Dish dish){
        return dishRepository.save(dish);
    }

    public void deleteDish(final Long id){
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllGlutenFreeAndVegetarian(boolean isGlutenFree, boolean isVegetarian) {
        return dishRepository.findAllByGlutenFreeAndVegetarian(isGlutenFree,isVegetarian);
    }

    public List<Dish> getAllGlutenFreeAndVegan(boolean isGlutenFree, boolean isVegan) {
        return dishRepository.findAllByGlutenFreeAndVegan(isGlutenFree, isVegan);
    }

    public List<Dish> getAllVeganAndVegetarian(boolean isVegan, boolean isVegetarian) {
        return dishRepository.findAllByVeganAndVegetarian(isVegan,isVegetarian);
    }

    public List<Dish> getAllGlutenFreeAndVeganAndVegetarian(boolean isGlutenFree, boolean isVegan, boolean isVegetarian) {
        return dishRepository.findAllByGlutenFreeAndVeganAndVegetarian(isGlutenFree,isVegan,isVegetarian);
    }
}
