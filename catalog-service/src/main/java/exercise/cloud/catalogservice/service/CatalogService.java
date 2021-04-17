package exercise.cloud.catalogservice.service;

import exercise.cloud.catalogservice.entity.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getAllCatalogs();
}
