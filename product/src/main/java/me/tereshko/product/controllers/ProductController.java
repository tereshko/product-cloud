package me.tereshko.product.controllers;

import lombok.RequiredArgsConstructor;
import me.tereshko.product.dto.ProductDto;
import me.tereshko.product.exceptions_handling.ResourceNotFoundException;
import me.tereshko.product.models.Product;
import me.tereshko.product.repositories.specifications.ProductSpecifications;
import me.tereshko.product.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Map<String, Object>> findAllProducts(@RequestParam MultiValueMap<String, String> params,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);

        Page<ProductDto> productPage = productService.findAll(ProductSpecifications.build(params), paging);

        List<ProductDto> productList = productPage.stream().map(ProductDto::new).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", productList);
        response.put("totalPages", productPage.getTotalPages());
        response.put("currentPage", productPage.getNumber());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void removeProductById(@PathVariable Long id) {
        productService.removeProductById(id);
    }

}
