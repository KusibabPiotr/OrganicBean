package ka.piotr.organicbean.product.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENU")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Menu {

    @Id
    @Column(name = "ID",unique = true)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "menu",
            targetEntity = Dish.class)
    private List<Dish> dishList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "menu",
            targetEntity = Drink.class)
    private List<Drink> drinkList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "menu",
            targetEntity = Product.class)
    private List<Product> productList = new ArrayList<>();

    //    private static MenuDto menuDto = null;
//
//    private MenuDto(){}
//
//    private MenuDto(List<Dish> dishList, List<Drink> drinkList, List<Product> productList) {
//        this.dishList = dishList;
//        this.drinkList = drinkList;
//        this.productList = productList;
//    }
//
//    public static MenuDto getInstance() {
//        if (menuDto == null) {
//            menuDto = new MenuDto(new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//        }
//        return menuDto;
//    }


}
