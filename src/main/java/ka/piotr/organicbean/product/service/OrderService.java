package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
