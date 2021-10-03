package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public List<Drink> getDrinks() {
        return drinkRepository.findAll();
    }

    public Optional<Drink> getDrink(Long id) {
        return drinkRepository.findById(id);
    }

    public Drink saveDrink(Drink drink){
        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long id){
        drinkRepository.deleteById(id);
    }
}
