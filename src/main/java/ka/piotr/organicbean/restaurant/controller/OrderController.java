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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List <OrderDto> getAllOrders(){
        return OrderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId)
            throws OrderNotFoundException{
        return OrderMapper.mapToOrderDto(orderService.getOrder(orderId));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PostMapping
    public OrderDto createOrder(){
        return OrderMapper.mapToOrderDto(orderService.createOrder());
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PatchMapping(value = "/{orderId}/dishes/{dishId}")
    public OrderDto addDishToOrder(@PathVariable Long orderId,
                                   @PathVariable Long dishId)
            throws DishNotFoundException,OrderNotFoundException{
        return OrderMapper.mapToOrderDto(
                orderService.addDishToOrder(orderId,dishId));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PatchMapping(value = "/{orderId}/customer",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addCustomerToOrder(@PathVariable Long orderId,
                                       @RequestBody @Valid CustomerDto customerDto )
            throws OrderNotFoundException {
        return OrderMapper.mapToOrderDto(
                orderService.addCustomerToOrder(
                        orderId,CustomerMapper.mapToCustomer(customerDto)));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PutMapping(value = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody @Valid OrderDto orderDto,
                                @PathVariable Long orderId)
            throws OrderNotFoundException {
        return OrderMapper.mapToOrderDto(
                orderService.updateOrder(OrderMapper.mapToOrder(orderDto),orderId));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @DeleteMapping(value = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId)
            throws IllegalArgumentException{
        orderService.deleteOrder(orderId);
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @DeleteMapping(value = "/{orderId}/dishes/{dishId}")
    public void removeDishFromOrder(@PathVariable Long orderId,
                                    @PathVariable Long dishId)
            throws OrderNotFoundException, DishNotFoundException {
        orderService.removeDishFromOrder(orderId,dishId);
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @DeleteMapping(value = "/{orderId}/customer")
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
