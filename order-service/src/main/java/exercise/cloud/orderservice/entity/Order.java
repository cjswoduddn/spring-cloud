package exercise.cloud.orderservice.entity;

import exercise.cloud.orderservice.dto.OrderDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;
    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
    private String userId;
    private String orderId;

    private LocalDateTime createdAt;

    private Order(OrderDto orderDto){
        productId = orderDto.getProductId();
        quantity = orderDto.getQuantity();
        unitPrice = orderDto.getUnitPrice();
        totalPrice = orderDto.getTotalPrice();
        userId = orderDto.getUserId();
        orderId = orderDto.getOrderId();
        createdAt = LocalDateTime.now();
    }

    public static Order createOrder(OrderDto orderDto) {
        return
            new Order(orderDto);
    }
}
