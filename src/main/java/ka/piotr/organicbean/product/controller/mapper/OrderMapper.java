package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.model.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(order.getId(),
                order.getDishes(),
                order.getCustomer(),
                order.getOrderStatus());
    }

    public Order mapToOrder(final OrderDto orderDto){
        return new Order(orderDto.getId(),
                orderDto.getDishes(),
                orderDto.getCustomer(),
                orderDto.getOrderStatus());
    }

    public Order mapToOrder(final OrderDto orderDto, final Long id){
        return new Order(id,
                orderDto.getDishes(),
                orderDto.getCustomer(),
                orderDto.getOrderStatus());
    }

    public List <OrderDto> mapToOrderDtoList(final List <Order> orderList){
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
