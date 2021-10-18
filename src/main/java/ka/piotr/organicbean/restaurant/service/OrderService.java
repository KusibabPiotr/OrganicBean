package ka.piotr.organicbean.restaurant.service;

import ka.piotr.organicbean.restaurant.exceptions.DishNotFoundException;
import ka.piotr.organicbean.restaurant.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.restaurant.model.OrderStatus;
import ka.piotr.organicbean.restaurant.model.domain.Customer;
import ka.piotr.organicbean.restaurant.model.domain.Dish;
import ka.piotr.organicbean.restaurant.model.domain.Order;
import ka.piotr.organicbean.restaurant.repository.CustomerRepository;
import ka.piotr.organicbean.restaurant.repository.DishRepository;
import ka.piotr.organicbean.restaurant.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(){

        Order order = new Order();
        order.setOrderStatus(OrderStatus.NEW_ORDER);
        order.setDishes(List.of());
        order.setCustomer(null);
        order.setTotal(BigDecimal.ZERO);

        return orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long id){
        return orderRepository.findById(id);
    }

    public Order addDishToOrder(Long orderId, Long dishId)
            throws DishNotFoundException, OrderNotFoundException{

        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(DishNotFoundException::new);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        order.setTotal(order.getTotal().add(dish.getPrice()));
        order.getDishes().add(dish);
        return orderRepository.save(order);
    }

    public Order addCustomerToOrder(Long id, Customer customer)
            throws OrderNotFoundException {

        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public void removeDishFromOrder(Long orderId, Long dishId)
            throws OrderNotFoundException, DishNotFoundException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        Dish dish = order.getDishes().stream()
                .filter(e -> e.getId().equals(dishId))
                .findFirst()
                .orElseThrow(DishNotFoundException::new);

        order.getDishes().remove(dish);
        order.setTotal(order.getTotal().subtract(dish.getPrice()));
        orderRepository.save(order);
    }

    public void removeCustomerFromOrder(Long orderId)
            throws OrderNotFoundException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        order.setCustomer(null);
        orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) throws IllegalArgumentException{
        orderRepository.deleteById(id);
    }

}
