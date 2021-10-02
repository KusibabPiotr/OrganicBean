package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDish(Long id){
        return dishRepository.findById(id);
    }

    public Dish createDish(Dish dish){
        return dishRepository.save(dish);
    }

    public Dish updateDish(Dish dish){
        dishRepository.save(dish);
    }
}
