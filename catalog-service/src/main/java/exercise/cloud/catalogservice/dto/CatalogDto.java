package exercise.cloud.catalogservice.dto;

import exercise.cloud.catalogservice.entity.Catalog;
import lombok.Getter;

@Getter
public class CatalogDto {
    private String productId;
    private int quantity;
    private int unitPrice;
    private int totalPrice;

    private String orderId;
    private String userId;

}
