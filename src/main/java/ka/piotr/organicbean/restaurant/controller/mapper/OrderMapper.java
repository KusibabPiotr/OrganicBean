package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Order;
import ka.piotr.organicbean.restaurant.model.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDto mapToOrderDto(final Order order){
        return new OrderDto(order.getId(),
                order.getDishes(),
                order.getCustomer(),
                order.getOrderStatus(),
                order.getTotal());
    }

    public static Order mapToOrder(final OrderDto orderDto){
        return new Order(orderDto.getId(),
                orderDto.getDishes(),
                orderDto.getCustomer(),
                orderDto.getOrderStatus(),
                orderDto.getTotal());
    }

    public static List <OrderDto> mapToOrderDtoList(final List <Order> orderList){
        return orderList.stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
