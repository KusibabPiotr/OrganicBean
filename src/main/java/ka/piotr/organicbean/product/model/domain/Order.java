package ka.piotr.organicbean.product.model.domain;

import ka.piotr.organicbean.product.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private List <Dish> dishList;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
