package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Dish;
import ka.piotr.organicbean.restaurant.model.dto.DishDto;

import java.util.List;
import java.util.stream.Collectors;

public class DishMapper {

    private DishMapper() {
    }

    public static Dish mapToDish(final DishDto dishDto){
        return new Dish(dishDto.getId(),
                dishDto.getName(),
                dishDto.getDescription(),
                dishDto.getPrice(),
                dishDto.getKcal(),
                AllergenMapper.mapToAllergenSet(dishDto.getAllergens()),
                dishDto.getDishType());
    }

    public static DishDto mapToDishDto(final Dish dish){
        return new DishDto(dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getKcal(),
                AllergenMapper.mapToAllergenDtoSet(dish.getAllergens()),
                dish.getDishType());
    }



    public static List<DishDto> mapToDishDtoList(final List<Dish> dishes){
        return dishes.stream()
                .map(DishMapper::mapToDishDto)
                .collect(Collectors.toList());
    }
}
