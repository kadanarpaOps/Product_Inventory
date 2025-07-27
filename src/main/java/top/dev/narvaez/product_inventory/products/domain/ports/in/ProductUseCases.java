package top.dev.narvaez.product_inventory.products.domain.ports.in;

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

    List<ProductModel> findAllAvailableProducts(Long id);

    List<ProductModel> findAllDisabledProducts(Long id);

    List<ProductModel> findAllProducts(Long id);

    List<ProductModel> findProductsByCustomSearch(
            String name, String category, BigDecimal minPrice, BigDecimal maxPrice, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active);

    boolean disableProductById(Long id);

    boolean activateProductById(Long id);

    StockSuitability verifyStockSuitability(ProductModel toVerifyProduct, Long oldProductId);

    StockSuitability verifyStockSuitability(ProductModel toVerifyProduct);

}