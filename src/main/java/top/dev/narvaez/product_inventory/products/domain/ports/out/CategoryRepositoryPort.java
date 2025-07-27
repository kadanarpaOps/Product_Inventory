package top.dev.narvaez.product_inventory.products.domain.ports.out;

import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;

import java.util.Locale;
import java.util.Optional;

public interface CategoryRepositoryPort {

    Optional<CategoryModel> selectByName(ProductCategory category);

}
