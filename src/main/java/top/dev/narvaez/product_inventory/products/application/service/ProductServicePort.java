package top.dev.narvaez.product_inventory.products.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.domain.ports.in.ProductUseCases;
import top.dev.narvaez.product_inventory.common.application.util.ProvisionalConstants;
import top.dev.narvaez.product_inventory.products.domain.ports.in.StockSuitability;
import top.dev.narvaez.product_inventory.products.domain.ports.out.CategoryRepositoryPort;
import top.dev.narvaez.product_inventory.products.domain.ports.out.ProductRepositoryPort;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServicePort implements ProductUseCases {

    private final ProductRepositoryPort productRepository;

    private final CategoryRepositoryPort categoryRepository;

    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        fillNullValues(productModel);

        switch (verifyStockSuitability(productModel)) {
            case LOWER_THAN_MIN -> throw new RuntimeException("Lower than minimum stock suitability");
            case GREATER_THAN_MAX -> throw new RuntimeException("Greater than maximum stock suitability");
        }

        return productRepository.saveProduct(productModel);
    }

    @Override
    public ProductModel updateProduct(ProductModel productModel, Long productId) {
        if (productModel.getId() == null) productModel.setId(productId);
        if (!productModel.getId().equals(productId))
            throw new RuntimeException("The REST product id does not match the BODY product id");

        fillNullValues(productModel);

        switch (verifyStockSuitability(productModel, productModel.getId())) {
            case LOWER_THAN_MIN -> throw new RuntimeException("Lower than minimum stock suitability");
            case GREATER_THAN_MAX -> throw new RuntimeException("Greater than maximum stock suitability");
        }

        ProductModel productFromEntity = findAnyProductById(productId);
        mapProducts(productModel, productFromEntity);
        return productRepository.saveProduct(productFromEntity);
    }

    @Override
    public ProductModel findAvailableProductById(Long id) {
        return productRepository.selectAvailableById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductModel findAnyProductById(Long id) {
        return productRepository.selectById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductModel findAvailableProductByName(String name) {
        return productRepository.selectAvailableByName(name)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductModel findAnyProductByName(String name) {
        return productRepository.selectByName(name)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ProductModel> findAllAvailableProducts(Long id) {
        return productRepository.selectAllAvailable();
    }

    @Override
    public List<ProductModel> findAllDisabledProducts(Long id) {
        return productRepository.selectAllDisabled();
    }

    @Override
    public List<ProductModel> findAllProducts(Long id) {
        return productRepository.selectAll();
    }

    @Override
    public List<ProductModel> findProductsByCustomSearch(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active) {
        return productRepository.selectByCustomSearch(
                name, category, minPrice, maxPrice, manufacturer, stock, minStock, maxStock, active
        );
    }

    @Override
    public boolean disableProductById(Long id) {
        ProductModel toDisableProduct = this.findAnyProductById(id);
        if (!toDisableProduct.isActive()) throw new RuntimeException("Product already disabled");
        toDisableProduct.setActive(false);
        productRepository.saveProduct(toDisableProduct);
        return true;
    }

    @Override
    public boolean activateProductById(Long id) {
        ProductModel toActivateProduct = this.findAnyProductById(id);
        if (toActivateProduct.isActive()) throw new RuntimeException("Product already active");
        toActivateProduct.setActive(true);
        productRepository.saveProduct(toActivateProduct);
        return true;
    }

    @Override
    public StockSuitability verifyStockSuitability(ProductModel toVerifyProduct, Long oldProductId) {
        return compareStocks(toVerifyProduct, this.findAnyProductById(oldProductId));
    }

    @Override
    public StockSuitability verifyStockSuitability(ProductModel toVerifyProduct) {
        return compareStocks(toVerifyProduct, toVerifyProduct);
    }

    private void fillNullValues(ProductModel productModel) {
        if (productModel.getCategory() == null)
            productModel.setCategory(categoryRepository.selectByName(ProductCategory.UNDEFINED).orElseThrow(EntityNotFoundException::new));
        if (productModel.getMinStock() == null) productModel.setMinStock(ProvisionalConstants.MIN_STOCK);
        if (productModel.getMaxStock() == null) productModel.setMaxStock(ProvisionalConstants.MAX_STOCK);
        if (productModel.getManufacturer() == null) productModel.setManufacturer(ProvisionalConstants.MANUFACTURER);
    }

    private void mapProducts(ProductModel productModel, ProductModel productFromEntity) {
        productFromEntity.setId(productModel.getId());
        productFromEntity.setName(productModel.getName());
        productFromEntity.setDescription(productModel.getDescription());
        productFromEntity.setCategory(
                categoryRepository.selectByName(productModel.getCategory().getName()).get());
        productFromEntity.setPrice(productModel.getPrice());
        productFromEntity.setManufacturer(productModel.getManufacturer());
        productFromEntity.setStock(productModel.getStock());
        productFromEntity.setMinStock(productModel.getMinStock());
        productFromEntity.setMaxStock(productModel.getMaxStock());
        productFromEntity.setActive(productModel.isActive());
    }

    private StockSuitability compareStocks(ProductModel toVerifyProduct, ProductModel compareProduct) {
        if (toVerifyProduct.getStock().compareTo(compareProduct.getMinStock()) < 0) {
            return StockSuitability.LOWER_THAN_MIN;
        } else if (toVerifyProduct.getStock().compareTo(compareProduct.getMaxStock()) > 0) {
            return StockSuitability.GREATER_THAN_MAX;
        } else  if (toVerifyProduct.getStock().compareTo(compareProduct.getMaxStock()) == 0) {
            return StockSuitability.EQUALS_MAX;
        }
        return StockSuitability.OK;
    }

}