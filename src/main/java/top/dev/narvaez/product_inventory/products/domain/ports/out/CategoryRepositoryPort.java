package top.dev.narvaez.product_inventory.products.domain.ports.out;

import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {

    CategoryModel saveCategory(CategoryModel category);

    Optional<CategoryModel> selectByName(ProductCategory category);

    List<CategoryModel> selectAll();

    Optional<CategoryModel> selectById(Long id);

}
