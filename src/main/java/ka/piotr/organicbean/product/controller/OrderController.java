package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.OrderMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.model.dto.OrderDto;
import ka.piotr.organicbean.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "get/{id}")
    public OrderDto getOrder(@PathVariable Long id) throws OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.getOrder(id)
                .orElseThrow(OrderNotFoundException::new));
    }

    @PostMapping(value = "create",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderMapper.mapToOrderDto(orderService.createOrder(orderMapper.mapToOrder(orderDto)));
    }

    @PatchMapping(value = "partialUpdate/{id}")
    public OrderDto addDishToOrder(@PathVariable("id") Long orderId, @RequestParam Long dishId)
            throws DishNotFoundException,OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.addDishToOrder(orderId,dishId));
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @PathVariable Long id){
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto,id)));
    }


}
