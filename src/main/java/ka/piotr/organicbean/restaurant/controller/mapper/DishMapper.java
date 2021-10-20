package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Dish;
import ka.piotr.organicbean.restaurant.model.dto.DishDto;

import java.util.List;
import java.util.stream.Collectors;

public class DishMapper {

    private DishMapper() {
    }

    public static Dish mapToDish(final DishDto dishDto){
        return Dish.builder()
                .id(dishDto.getId())
                .name(dishDto.getName())
                .description(dishDto.getDescription())
                .price(dishDto.getPrice())
                .kcal(dishDto.getKcal())
                .allergens(AllergenMapper.mapToAllergenSet(dishDto.getAllergens()))
                .dishType(dishDto.getDishType())
                .build();
    }

    public static DishDto mapToDishDto(final Dish dish){
        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .kcal(dish.getKcal())
                .allergens(AllergenMapper.mapToAllergenDtoSet(dish.getAllergens()))
                .dishType(dish.getDishType())
                .build();
    }



    public static List<DishDto> mapToDishDtoList(final List<Dish> dishes){
        return dishes.stream()
                .map(DishMapper::mapToDishDto)
                .collect(Collectors.toList());
    }
}
