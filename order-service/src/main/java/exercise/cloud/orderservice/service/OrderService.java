package exercise.cloud.orderservice.service;

import exercise.cloud.orderservice.dto.OrderDto;
import exercise.cloud.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    List<Order> getAllOrdersByUserId(String userId);
}
