package ka.piotr.organicbean.product.model.domain;

import ka.piotr.organicbean.product.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "DISHES_ORDERS",
    joinColumns = {@JoinColumn(name = "order_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_ID",referencedColumnName = "id")})
    private List<Dish> dishes = new ArrayList<>();
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
