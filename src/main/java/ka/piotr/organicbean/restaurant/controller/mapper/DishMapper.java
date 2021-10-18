package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Dish;
import ka.piotr.organicbean.restaurant.model.dto.DishDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final AllergenMapper allergenMapper;

    public Dish mapToDish(final DishDto dishDto){
        return new Dish(dishDto.getId(),
                dishDto.getName(),
                dishDto.getDescription(),
                dishDto.getPrice(),
                dishDto.getKcal(),
                allergenMapper.mapToAllergenSet(dishDto.getAllergens()),
                dishDto.getDishType());
    }

    public Dish mapToDish(final DishDto dishDto,final Long id){
        return new Dish(id,
                dishDto.getName(),
                dishDto.getDescription(),
                dishDto.getPrice(),
                dishDto.getKcal(),
                allergenMapper.mapToAllergenSet(dishDto.getAllergens()),
                dishDto.getDishType());
    }

    public DishDto mapToDishDto(final Dish dish){
        return new DishDto(dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getKcal(),
                allergenMapper.mapToAllergenDtoSet(dish.getAllergens()),
                dish.getDishType());
    }



    public List<DishDto> mapToDishDtoList(final List<Dish> dishes){
        return dishes.stream()
                .map(this::mapToDishDto)
                .collect(Collectors.toList());
    }
}
