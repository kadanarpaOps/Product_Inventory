package top.dev.narvaez.product_inventory.products.domain.ports.out;

import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    ProductModel saveProduct(ProductModel product);

    List<ProductModel> selectAll();

    List<ProductModel> selectAllDisabled();

    List<ProductModel> selectAllAvailable();

    Optional<ProductModel> selectById(Long id);

    Optional<ProductModel> selectAvailableById(Long id);

    Optional<ProductModel> selectByName(String name);

    Optional<ProductModel> selectAvailableByName(String name);

    List<ProductModel> selectByCustomSearch(
            String name, String category, BigDecimal minPrice, BigDecimal maxPrice, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active);

}
