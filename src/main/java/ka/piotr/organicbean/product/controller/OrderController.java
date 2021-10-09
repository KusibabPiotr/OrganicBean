package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.OrderMapper;
import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.model.dto.OrderDto;
import ka.piotr.organicbean.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
