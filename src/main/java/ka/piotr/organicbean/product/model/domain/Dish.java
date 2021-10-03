package ka.piotr.organicbean.product.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "ID",unique = true)
    private Long id;
    @NotNull
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;
    @NotNull
    @Column(name = "KCAL")
    private int kcal;
    @Column(name = "GLUTEN_FREE")
    private boolean glutenFree;
    @Column(name = "VEGAN")
    private boolean vegan;
    @Column(name = "VEGETARIAN")
    private boolean vegetarian;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FOOD_TYPE")
    private FoodType foodType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    @JsonIgnore
    private Menu menu;
}
