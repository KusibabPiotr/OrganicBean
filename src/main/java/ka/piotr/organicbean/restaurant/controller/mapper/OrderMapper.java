package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Order;
import ka.piotr.organicbean.restaurant.model.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDto mapToOrderDto(final Order order){
        return OrderDto.builder()
                .id(order.getId())
                .dishes(order.getDishes())
                .customer(order.getCustomer())
                .orderStatus(order.getOrderStatus())
                .total(order.getTotal())
                .build();
    }

    public static Order mapToOrder(final OrderDto orderDto){
        return Order.builder()
                .id(orderDto.getId())
                .dishes(orderDto.getDishes())
                .customer(orderDto.getCustomer())
                .orderStatus(orderDto.getOrderStatus())
                .total(orderDto.getTotal())
                .build();
    }

    public static List <OrderDto> mapToOrderDtoList(final List <Order> orderList){
        return orderList.stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
