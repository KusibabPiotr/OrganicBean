package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.exceptions.CustomerNotFoundException;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.product.model.OrderStatus;
import ka.piotr.organicbean.product.model.domain.Customer;
import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(){

        Order order = new Order();
        order.setOrderStatus(OrderStatus.NEW_ORDER);
        order.setDishes(List.of());
        order.setCustomer(null);

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

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

}
