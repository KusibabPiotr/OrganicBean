package ka.piotr.organicbean.restaurant.model.domain;

import ka.piotr.organicbean.restaurant.model.DishType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DISHES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public final class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int kcal;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set <Allergen> allergens = new HashSet<>();

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
