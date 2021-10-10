package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.CustomerMapper;
import ka.piotr.organicbean.product.controller.mapper.OrderMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.product.model.dto.CustomerDto;
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
    private final CustomerMapper customerMapper;

//  for admin
    @GetMapping("get")
    public List <OrderDto> getAllOrders(){
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }
//  for user
    @GetMapping(value = "get/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId)
                .orElseThrow(OrderNotFoundException::new));
    }
//  for user
    @PostMapping(value = "create")
    public OrderDto createOrder(){
        return orderMapper.mapToOrderDto(orderService.createOrder());
    }
//  for user
    @PatchMapping(value = "{orderId}/addDish")
    public OrderDto addDishToOrder(@PathVariable Long orderId, @RequestParam Long dishId)
            throws DishNotFoundException,OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.addDishToOrder(orderId,dishId));
    }
//  for user
    @PatchMapping(value = "{orderId}/addCustomer",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addCustomerToOrder(@PathVariable Long orderId, @RequestBody CustomerDto customerDto )
            throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.addCustomerToOrder(orderId,customerMapper.mapToCustomer(customerDto)));
    }
//  for admin
    @PutMapping(value = "update/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @PathVariable Long orderId){
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto,orderId)));
    }
//  for user/admin
    @DeleteMapping(value = "delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }

//  for user
    @DeleteMapping(value = "{orderId}/removeDish")
    public void removeDishFromOrder(@PathVariable Long orderId, @RequestParam Long dishId)
            throws OrderNotFoundException, DishNotFoundException {
        orderService.removeDishFromOrder(orderId,dishId);
    }

}
