package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.product.model.OrderStatus;
import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.repository.DishRepository;
import ka.piotr.organicbean.product.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order){
        order.setOrderStatus(OrderStatus.NEW_ORDER);
        order.setDishList(List.of());
        order.setCustomer(null);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long id){
        return orderRepository.findById(id);
    }

    public Order addDishToOrder(Long orderId, Long dishId)
            throws DishNotFoundException, OrderNotFoundException{
        Dish dish = dishRepository.findById(dishId).orElseThrow(DishNotFoundException::new);
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.getDishList().add(dish);
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        return orderRepository.save(order);
    }


}
