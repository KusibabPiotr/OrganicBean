package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.OrderStatus;
import ka.piotr.organicbean.product.model.domain.Customer;
import ka.piotr.organicbean.product.model.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class OrderDto {

    private Long id;
    private List<Dish> dishList;
    private Customer customer;
    private OrderStatus orderStatus;
}
