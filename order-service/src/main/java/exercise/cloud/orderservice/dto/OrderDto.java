package exercise.cloud.orderservice.dto;

import exercise.cloud.orderservice.entity.Order;
import exercise.cloud.orderservice.vo.RequestOrder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderDto {

    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private String userId;
    private String orderId;

    private OrderDto(Order order){
        productId = order.getProductId();
        quantity = order.getQuantity();
        unitPrice = order.getUnitPrice();
        totalPrice = order.getTotalPrice();
        userId = order.getUserId();
        orderId = order.getOrderId();
    }

    private OrderDto(RequestOrder requestOrder){
        productId = requestOrder.getProductId();
        quantity = requestOrder.getQuantity();
        unitPrice = requestOrder.getUnitPrice();
        totalPrice = quantity*unitPrice;
        orderId = UUID.randomUUID().toString();
    }

    public static OrderDto createOrderDto(Order order) {
        return new OrderDto(order);
    }

    public static OrderDto createOrderDto(RequestOrder requestOrder, String userId){
        OrderDto orderDto = new OrderDto(requestOrder);
        orderDto.userId = userId;
        return orderDto;
    }
}
