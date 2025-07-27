package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.domain.ports.out.ProductRepositoryPort;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.mapper.ProductPersistenceMapper;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository.JpaProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository productRepository;

    private final ProductPersistenceMapper mapper;

    @Override
    public ProductModel saveProduct(ProductModel product) {
        return mapper.toModel(productRepository.save(mapper.toEntity(product)));
    }

    @Override
    public List<ProductModel> selectAll() {
        return productRepository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public List<ProductModel> selectAllDisabled() {
        return productRepository.findAllDisabled().stream().map(mapper::toModel).toList();
    }

    @Override
    public List<ProductModel> selectAllAvailable() {
        return productRepository.findAllAvailable().stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<ProductModel> selectById(Long id) {
        return productRepository.findById(id).map(mapper::toModel);
    }

    @Override
    public Optional<ProductModel> selectAvailableById(Long id) {
        return productRepository.findAvailableById(id).map(mapper::toModel);
    }

    @Override
    public Optional<ProductModel> selectByName(String name) {
        return productRepository.findByName(name).map(mapper::toModel);
    }

    @Override
    public Optional<ProductModel> selectAvailableByName(String name) {
        return productRepository.findAvailableByName(name).map(mapper::toModel);
    }

    @Override
    public List<ProductModel> selectByCustomSearch(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active) {
        return productRepository.findFilteredProducts(name, category, minPrice, maxPrice, manufacturer, stock, minStock, maxStock, active)
                .stream().map(mapper::toModel).toList();
    }
}
