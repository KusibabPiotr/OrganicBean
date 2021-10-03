package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getDishes() {
        return dishRepository.findAll();
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
}
