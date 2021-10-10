package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.OrderMapper;
import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.model.dto.OrderDto;
import ka.piotr.organicbean.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("get")
    public List <OrderDto> getAllOrders(){
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @PostMapping(value = "create",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderMapper.mapToOrderDto(orderMapper.mapToOrder(orderDto));
    }



}
