package ka.piotr.organicbean.product.model.domain;

import ka.piotr.organicbean.product.model.AllergenType;
import ka.piotr.organicbean.product.model.DishType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "DISHES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private int kcal;
//    private boolean glutenFree;
//    private boolean vegan;
//    private boolean vegetarian;
    @Enumerated(value = EnumType.STRING)
    private AllergenType allergenType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        return id.equals(dish.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
