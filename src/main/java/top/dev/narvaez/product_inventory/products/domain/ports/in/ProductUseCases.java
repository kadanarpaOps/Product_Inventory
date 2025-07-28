package top.dev.narvaez.product_inventory.products.domain.ports.in;

import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductUseCases {

    ProductModel saveProduct(ProductModel productModel);

    ProductModel updateProduct(ProductModel productModel, Long productId);

    ProductModel findAvailableProductById(Long id);

    ProductModel findAnyProductById(Long id);

    ProductModel findAvailableProductByName(String name);

    ProductModel findAnyProductByName(String name);

    List<ProductModel> findAllAvailableProducts();

    List<ProductModel> findAllDisabledProducts();

    List<ProductModel> findAllProducts();

    List<ProductModel> findProductsByCustomSearch(
            String name, ProductCategory category, BigDecimal minPrice, BigDecimal maxPrice, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active);

    boolean disableProductById(Long id);

    boolean activateProductById(Long id);

    StockSuitability verifyStockSuitability(ProductModel toVerifyProduct);

}