package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.model.OrderStatus;
import ka.piotr.organicbean.restaurant.model.domain.Customer;
import ka.piotr.organicbean.restaurant.model.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
@SessionScope
public class OrderDto {

    private Long id;
    private List<Dish> dishes;
    private Customer customer;
    private OrderStatus orderStatus;
    private BigDecimal total;

}
