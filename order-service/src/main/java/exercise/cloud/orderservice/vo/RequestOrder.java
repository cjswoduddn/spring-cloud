package exercise.cloud.orderservice.vo;

import lombok.Getter;

@Getter
public class RequestOrder {
    private String productId;
    private int quantity;
    private int unitPrice;
}
