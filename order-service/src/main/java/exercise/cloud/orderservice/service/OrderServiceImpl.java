package exercise.cloud.orderservice.service;

import exercise.cloud.orderservice.dto.OrderDto;
import exercise.cloud.orderservice.entity.Order;
import exercise.cloud.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderRepository.save(Order.createOrder(orderDto));
        return orderDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return
                OrderDto.createOrderDto(
                        orderRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException())
                );
    }

    @Override
    public List<Order> getAllOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
