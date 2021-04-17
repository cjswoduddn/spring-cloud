package exercise.cloud.orderservice.vo;

import exercise.cloud.orderservice.dto.OrderDto;
import exercise.cloud.orderservice.entity.Order;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;

@Getter
public class ResponseOrder {
    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private int stock;
    private LocalDateTime createdAt;
    private String orderId;

    private ResponseOrder(OrderDto orderDto){
        productId = orderDto.getProductId();
        quantity = orderDto.getQuantity();
        unitPrice = orderDto.getUnitPrice();
        totalPrice = orderDto.getTotalPrice();
        stock = orderDto.getQuantity();
        createdAt = LocalDateTime.now();
        orderId = orderDto.getOrderId();
    }

    private ResponseOrder(Order order){
        productId = order.getProductId();
        quantity = order.getQuantity();
        unitPrice = order.getUnitPrice();
        totalPrice = order.getTotalPrice();
        stock = order.getQuantity();
        createdAt = LocalDateTime.now();
        orderId = order.getOrderId();
    }

    public static ResponseOrder createResponseOrder(OrderDto orderDto){
        return
            new ResponseOrder(orderDto);
    }

    public static ResponseOrder createResponseOrder(Order order){
        return
                new ResponseOrder(order);
    }

}
