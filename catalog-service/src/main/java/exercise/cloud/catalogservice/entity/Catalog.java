package exercise.cloud.catalogservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "catalog")
public class Catalog {

    @Id @GeneratedValue
    private Long id;
    private String productId;
    private String productName;
    private int stock;
    private int unitPrice;
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
