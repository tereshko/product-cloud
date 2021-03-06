package me.tereshko.product.services;

import lombok.RequiredArgsConstructor;
import me.tereshko.product.dto.ProductDto;
import me.tereshko.product.models.Product;
import me.tereshko.product.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec,pageable).map(ProductDto::new);
    }

    public List<ProductDto> findAllProductsDto() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }
}