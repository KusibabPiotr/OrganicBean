package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.repository.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    private DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

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
