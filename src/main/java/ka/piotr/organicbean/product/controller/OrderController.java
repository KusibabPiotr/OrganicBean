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

//    for admin
    @GetMapping("get")
    public List <OrderDto> getAllOrders(){
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }
//     for user
    @GetMapping(value = "get/{id}")
    public OrderDto getOrder(@PathVariable Long id) throws OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.getOrder(id)
                .orElseThrow(OrderNotFoundException::new));
    }
//      for user
    @PostMapping(value = "create",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return orderMapper.mapToOrderDto(orderService.createOrder(orderMapper.mapToOrder(orderDto)));
    }
//      for user
    @PatchMapping(value = "{id}/addDish")
    public OrderDto addDishToOrder(@PathVariable("id") Long orderId, @RequestParam Long dishId)
            throws DishNotFoundException,OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.addDishToOrder(orderId,dishId));
    }
//      for user
    @PatchMapping(value = "{id}/addCustomer",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addCustomerToOrder(@PathVariable Long id, @RequestBody CustomerDto customerDto )
            throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.addCustomerToOrder(id,customerMapper.mapToCustomer(customerDto)));
    }
//      for admin
    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @PathVariable Long id){
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto,id)));
    }
//      for user/admin
    @DeleteMapping(value = "delete/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
//      for user
    @DeleteMapping(value = "{id}/removeDish")
    public void removeDishFromOrder(@PathVariable("id") Long orderId, @RequestParam Long dishId)
            throws OrderNotFoundException, DishNotFoundException {
        orderService.removeDishFromOrder(orderId,dishId);
    }

}
