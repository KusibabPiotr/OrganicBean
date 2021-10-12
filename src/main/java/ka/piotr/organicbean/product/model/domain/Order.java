package ka.piotr.organicbean.product.model.domain;

import ka.piotr.organicbean.product.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = CascadeType.PERSIST)
//    @BatchSize(size = 5)
    @JoinTable(name = "DISHES_ORDERS",
    joinColumns = {@JoinColumn(name = "order_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_ID",referencedColumnName = "id")})
    private Set<Dish> dishList = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
