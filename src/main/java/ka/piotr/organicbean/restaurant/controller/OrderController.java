package ka.piotr.organicbean.restaurant.controller;

import ka.piotr.organicbean.restaurant.controller.mapper.CustomerMapper;
import ka.piotr.organicbean.restaurant.controller.mapper.OrderMapper;
import ka.piotr.organicbean.restaurant.exceptions.DishNotFoundException;
import ka.piotr.organicbean.restaurant.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.restaurant.model.dto.CustomerDto;
import ka.piotr.organicbean.restaurant.model.dto.OrderDto;
import ka.piotr.organicbean.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @GetMapping
    public List <OrderDto> getAllOrders(){
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId)
                .orElseThrow(OrderNotFoundException::new));
    }

    @PostMapping
    public OrderDto createOrder(){
        return orderMapper.mapToOrderDto(orderService.createOrder());
    }

    @PatchMapping(value = "/{orderId}/dishes/{dishId}")
    public OrderDto addDishToOrder(@PathVariable Long orderId, @PathVariable Long dishId)
            throws DishNotFoundException,OrderNotFoundException{
        return orderMapper.mapToOrderDto(
                orderService.addDishToOrder(orderId,dishId));
    }

    @PatchMapping(value = "/{orderId}/customers",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addCustomerToOrder(@PathVariable Long orderId, @RequestBody @Valid CustomerDto customerDto )
            throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(
                orderService.addCustomerToOrder(
                        orderId,customerMapper.mapToCustomer(customerDto)));
    }

    @PutMapping(value = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable Long orderId){
        return orderMapper.mapToOrderDto(
                orderService.updateOrder(
                        orderMapper.mapToOrder(orderDto,orderId)));
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId)
            throws IllegalArgumentException{
        orderService.deleteOrder(orderId);
    }

    @DeleteMapping(value = "/{orderId}/dishes/{dishId}")
    public void removeDishFromOrder(@PathVariable Long orderId, @PathVariable Long dishId)
            throws OrderNotFoundException, DishNotFoundException {
        orderService.removeDishFromOrder(orderId,dishId);
    }
    @DeleteMapping(value = "/{orderId}/customers/{customerId}")
    public void removeCustomerFromOrder(@PathVariable Long orderId, @PathVariable Long customerId)
            throws OrderNotFoundException {
        orderService.removeCustomerFromOrder(orderId,customerId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
