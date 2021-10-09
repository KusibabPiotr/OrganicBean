package ka.piotr.organicbean.product.model.domain;

import ka.piotr.organicbean.product.model.DishType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "DISHES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public final class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private int kcal;
    private boolean glutenFree;
    private boolean vegan;
    private boolean vegetarian;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;
}
