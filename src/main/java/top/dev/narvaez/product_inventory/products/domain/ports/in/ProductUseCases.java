package top.dev.narvaez.product_inventory.products.domain.ports.in;

import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductUseCases {

    ProductModel saveProduct(ProductModel productModel);

    void updateProduct(ProductModel productModel);

    ProductModel findAvailableProductById(Long id);

    ProductModel findAnyProductById(Long id);

    ProductModel findAvailableProductByName(Long id);

    ProductModel findAnyProductByName(Long id);

    List<ProductModel> findAllAvailableProducts(Long id);

    List<ProductModel> findAllDisabledProducts(Long id);

    List<ProductModel> findAllProducts(Long id);

    List<ProductModel> findProductsByCustomSearch(
            Long id, String name, String description, String category, BigDecimal price, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active);

    boolean disableProductById(Long id);

    StockSuitability verifyStockSuitability(ProductModel toVerifyProduct, String oldProductId);

}