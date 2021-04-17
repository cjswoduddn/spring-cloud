package exercise.cloud.catalogservice.repository;

import exercise.cloud.catalogservice.entity.Catalog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatalogRepository extends CrudRepository<Catalog, Long> {

    List<Catalog> findAllBy();
}
