package exercise.cloud.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import exercise.cloud.catalogservice.entity.Catalog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private int unitPrice;
    private int stock;
    private LocalDateTime createdAt;

    private ResponseCatalog(Catalog catalog){
        productId = catalog.getProductId();
        productName = catalog.getProductName();
        unitPrice = catalog.getUnitPrice();
        stock = catalog.getStock();
        createdAt = catalog.getCreatedAt();
    }

    public static ResponseCatalog createResponseCatalog(Catalog catalog){
        return new ResponseCatalog(catalog);
    }
}
