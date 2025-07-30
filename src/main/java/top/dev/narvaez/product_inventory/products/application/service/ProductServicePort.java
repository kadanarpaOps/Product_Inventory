package top.dev.narvaez.product_inventory.products.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.listeners.domain.ports.in.AuditProductUseCases;
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

    private final AuditProductUseCases auditService;

    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        fillNullValuesToCreate(productModel);
        verifyValidStock(productModel);

        return productRepository.saveProduct(productModel);
    }

    @Override
    public ProductModel updateProduct(ProductModel productModel, Long productId) {
        if (productModel.getId() == null) productModel.setId(productId);
        if (!productModel.getId().equals(productId))
            throw new RuntimeException("The REST product id does not match the BODY product id");

        ProductModel productFromEntity = findAnyProductById(productId);
        fillNullValuesToUpdate(productModel, productFromEntity);

        verifyValidStock(productModel);

        mapProducts(productModel, productFromEntity);
        auditService.setAuditUpdateParams();
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
    public List<ProductModel> findAllAvailableProducts() {
        return productRepository.selectAllAvailable();
    }

    @Override
    public List<ProductModel> findAllDisabledProducts() {
        return productRepository.selectAllDisabled();
    }

    @Override
    public List<ProductModel> findAllProducts() {
        return productRepository.selectAll();
    }

    @Override
    public List<ProductModel> findProductsByCustomSearch(String name, ProductCategory category, BigDecimal minPrice, BigDecimal maxPrice, String manufacturer, Integer stock, Integer minStock, Integer maxStock, boolean active) {
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
    public StockSuitability verifyStockSuitability(ProductModel toVerifyProduct) {
        return compareStocks(toVerifyProduct);
    }

    public void verifyValidStock(ProductModel productModel) {
        switch (verifyStockSuitability(productModel)) {
            case LOWER_THAN_MIN -> throw new RuntimeException("Lower than minimum stock suitability");
            case GREATER_THAN_MAX -> throw new RuntimeException("Greater than maximum stock suitability");
        }
    }

    private void fillNullValuesToCreate(ProductModel productModel) {
        if (productModel.getCategory() == null)
            productModel.setCategory(categoryRepository.selectByName(ProductCategory.UNDEFINED).orElseThrow(EntityNotFoundException::new));
        else  productModel.setCategory(categoryRepository.selectByName(ProductCategory.valueOf(productModel.getCategory().getName().name())).get());
        if (productModel.getStock() == null) productModel.setStock(ProvisionalConstants.MIN_STOCK);
        if (productModel.getMinStock() == null) productModel.setMinStock(ProvisionalConstants.MIN_STOCK);
        if (productModel.getMaxStock() == null) productModel.setMaxStock(ProvisionalConstants.MAX_STOCK);
        if (productModel.getManufacturer() == null) productModel.setManufacturer(ProvisionalConstants.MANUFACTURER);
    }

    private void fillNullValuesToUpdate(ProductModel productModel, ProductModel productFromEntity) {
        if (productModel.getName() == null) productModel.setName(productFromEntity.getName());
        if (productModel.getDescription() == null) productModel.setDescription(productFromEntity.getDescription());
        if (productModel.getPrice() == null) productModel.setPrice(productFromEntity.getPrice());
        if (productModel.getManufacturer() == null) productModel.setManufacturer(productFromEntity.getManufacturer());
        if (productModel.getCategory() == null) productModel.setCategory(productFromEntity.getCategory());
        if (productModel.getStock() == null) productModel.setStock(productFromEntity.getStock());
        if (productModel.getMinStock() == null) productModel.setMinStock(productFromEntity.getMinStock());
        if (productModel.getMaxStock() == null) productModel.setMaxStock(productFromEntity.getMaxStock());
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
    }

    private StockSuitability compareStocks(ProductModel toVerifyProduct) {
        if (toVerifyProduct.getStock().compareTo(toVerifyProduct.getMinStock()) < 0) {
            return StockSuitability.LOWER_THAN_MIN;
        } else if (toVerifyProduct.getStock().compareTo(toVerifyProduct.getMaxStock()) > 0) {
            return StockSuitability.GREATER_THAN_MAX;
        } else  if (toVerifyProduct.getStock().compareTo(toVerifyProduct.getMaxStock()) == 0) {
            return StockSuitability.EQUALS_MAX;
        }
        return StockSuitability.OK;
    }

}