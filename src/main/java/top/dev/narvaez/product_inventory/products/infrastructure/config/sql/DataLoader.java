package top.dev.narvaez.product_inventory.products.infrastructure.config.sql;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.domain.ports.in.ProductUseCases;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.adapter.CategoryRepositoryAdapter;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.adapter.ProductRepositoryAdapter;

import java.math.BigDecimal;
import java.util.Arrays;

@AllArgsConstructor
@Service
public class DataLoader implements CommandLineRunner {

    private final CategoryRepositoryAdapter categoryRepository;

    private final ProductRepositoryAdapter productRepository;

    private final ProductUseCases productService;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.verifyRepositoryReady()) {
            Arrays.stream(ProductCategory.values()).forEach(category -> {
                if (!categoryRepository.selectByName(category).isPresent()) {
                    categoryRepository.saveCategory(new CategoryModel(null, category, category.name().toLowerCase()));
                }
            });
        }
        if (productRepository.verifyRepositoryReady()) {
            productService.saveProduct(new ProductModel(
                    null,
                    "Acer Aspire 3",
                    null,
                    new CategoryModel(12L, ProductCategory.ELECTRONICS, null),
                    BigDecimal.valueOf(2300000.00),
                    "ACER",
                    null,
                    null,
                    null,
                    false,
                    null,
                    null
            ));
            productService.saveProduct(new ProductModel(
                    null,
                    "Manzana Libra",
                    null,
                    new CategoryModel(1L, ProductCategory.FRUIT, null),
                    BigDecimal.valueOf(8900.00),
                    "Fruver el Corral",
                    null,
                    null,
                    null,
                    false,
                    null,
                    null
            ));
        }
    }
}