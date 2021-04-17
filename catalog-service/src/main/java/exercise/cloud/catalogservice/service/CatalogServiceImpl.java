package exercise.cloud.catalogservice.service;

import exercise.cloud.catalogservice.dto.CatalogDto;
import exercise.cloud.catalogservice.entity.Catalog;
import exercise.cloud.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @PostConstruct
    public void initDB(){
        Catalog catalog1 = new Catalog();
        catalog1.setProductName("Seoul");
        catalog1.setProductId("CATALOG-001");
        catalog1.setStock(100);
        catalog1.setUnitPrice(10000);
        catalog1.setCreatedAt(LocalDateTime.now());

        Catalog catalog2 = new Catalog();
        catalog2.setProductName("Busan");
        catalog2.setProductId("CATALOG-002");
        catalog2.setStock(110);
        catalog2.setUnitPrice(11000);
        catalog2.setCreatedAt(LocalDateTime.now());

        Catalog catalog3 = new Catalog();
        catalog3.setCreatedAt(LocalDateTime.now());
        catalog3.setProductName("Daejeon");
        catalog3.setProductId("CATALOG-003");
        catalog3.setStock(120);
        catalog3.setUnitPrice(12000);
        catalogRepository.save(catalog1);
        catalogRepository.save(catalog2);
        catalogRepository.save(catalog3);
    }

    @Override
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAllBy();
    }
}
