package exercise.cloud.orderservice.controller;

import exercise.cloud.orderservice.dto.OrderDto;
import exercise.cloud.orderservice.service.OrderService;
import exercise.cloud.orderservice.vo.RequestOrder;
import exercise.cloud.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder order) {
        return new ResponseEntity<>(
                ResponseOrder.createResponseOrder(orderService.createOrder(
                        OrderDto.createOrderDto(order, userId)
                        )
                ),
                HttpStatus.OK
        );
    }
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        return
                new ResponseEntity<>(
                        orderService.getAllOrdersByUserId(userId).stream().map(order -> ResponseOrder.createResponseOrder(order))
                        .collect(Collectors.toList()), HttpStatus.OK
                );
    }
}
