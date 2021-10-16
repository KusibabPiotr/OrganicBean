package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.CustomerMapper;
import ka.piotr.organicbean.product.controller.mapper.OrderMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.exceptions.OrderNotFoundException;
import ka.piotr.organicbean.product.model.dto.CustomerDto;
import ka.piotr.organicbean.product.model.dto.OrderDto;
import ka.piotr.organicbean.product.service.OrderService;
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

    @GetMapping("get")
    public List <OrderDto> getAllOrders(){
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @GetMapping(value = "get/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId)
                .orElseThrow(OrderNotFoundException::new));
    }

    @PostMapping(value = "create")
    public OrderDto createOrder(){
        return orderMapper.mapToOrderDto(orderService.createOrder());
    }

    @PatchMapping(value = "{orderId}/addDish")
    public OrderDto addDishToOrder(@PathVariable Long orderId, @RequestParam Long dishId)
            throws DishNotFoundException,OrderNotFoundException{
        return orderMapper.mapToOrderDto(orderService.addDishToOrder(orderId,dishId));
    }

    @PatchMapping(value = "{orderId}/addCustomer",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addCustomerToOrder(@PathVariable Long orderId, @RequestBody @Valid CustomerDto customerDto )
            throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.addCustomerToOrder(orderId,customerMapper.mapToCustomer(customerDto)));
    }

    @PutMapping(value = "update/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable Long orderId){
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto,orderId)));
    }

    @DeleteMapping(value = "delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }

    @DeleteMapping(value = "{orderId}/removeDish")
    public void removeDishFromOrder(@PathVariable Long orderId, @RequestParam Long dishId)
            throws OrderNotFoundException, DishNotFoundException {
        orderService.removeDishFromOrder(orderId,dishId);
    }
    @DeleteMapping(value = "{orderId}/removeCustomer")
    public void removeCustomerFromOrder(@PathVariable Long orderId)
            throws OrderNotFoundException {
        orderService.removeCustomerFromOrder(orderId);
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
