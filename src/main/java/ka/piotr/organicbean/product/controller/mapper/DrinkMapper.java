package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.model.dto.DrinkDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrinkMapper {

    public DrinkDto mapToDrinkDto(final Drink drink){
        return new DrinkDto(
                drink.getName(),
                drink.getDescription(),
                drink.getPrice(),
                drink.getKcal(),
                drink.getMenu());
    }

    public Drink mapToDrink(final DrinkDto drinkDto, final Long id){
        return new Drink(id,
                drinkDto.getName(),
                drinkDto.getDescription(),
                drinkDto.getPrice(),
                drinkDto.getKcal(),
                drinkDto.getMenu());
    }

    public List<DrinkDto> mapToDrinkDtoList(final List<Drink> drinks){
        return drinks.stream()
                .map(this::mapToDrinkDto)
                .collect(Collectors.toList());
    }
}
