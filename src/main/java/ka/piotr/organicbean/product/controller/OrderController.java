package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.model.domain.Order;
import ka.piotr.organicbean.product.model.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping("get")
    public List <OrderDto> getAllOrders(){

    }
}
