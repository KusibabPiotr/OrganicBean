package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.DishDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DishMapper {

    public Dish mapToDish(final DishDto dishDto){
        return new Dish(dishDto.getId(),
                dishDto.getName(),
                dishDto.getDescription(),
                dishDto.getPrice(),
                dishDto.getKcal(),
                dishDto.isGlutenFree(),
                dishDto.isVegan(),
                dishDto.isVegetarian(),
                dishDto.getFoodType(),
                dishDto.getMenu());
    }

    public DishDto mapToDishDto(final Dish dish){
        return new DishDto(dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getKcal(),
                dish.isGlutenFree(),
                dish.isVegan(),
                dish.isVegetarian(),
                dish.getFoodType(),
                dish.getMenu());
    }

    public List<DishDto> mapToDishDtoList(final List<Dish> dishes){
        return dishes.stream()
                .map(this::mapToDishDto)
                .collect(Collectors.toList());
    }
}
