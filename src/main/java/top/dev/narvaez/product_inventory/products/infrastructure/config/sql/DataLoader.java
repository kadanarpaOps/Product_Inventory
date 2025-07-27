package top.dev.narvaez.product_inventory.products.infrastructure.config.sql;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.adapter.CategoryRepositoryAdapter;

import java.util.Arrays;

@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoryRepositoryAdapter categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.verifyRepositoryReady()) {
            Arrays.stream(ProductCategory.values()).forEach(category -> {
                if (!categoryRepository.selectByName(category).isPresent()) {
                    categoryRepository.saveCategory(new CategoryModel(null, category, null));
                }
            });
        }
    }
}
