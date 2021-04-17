package exercise.cloud.catalogservice.controller;

import exercise.cloud.catalogservice.service.CatalogService;
import exercise.cloud.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/catalog-service")
@RestController
public class CatalogController {


    private final CatalogService catalogService;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working well!";
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        return
                new ResponseEntity<>(
                        catalogService.getAllCatalogs().stream()
                                .map(catalog -> ResponseCatalog.createResponseCatalog(catalog))
                                .collect(Collectors.toList()),
                        HttpStatus.OK
                );
    }
}
