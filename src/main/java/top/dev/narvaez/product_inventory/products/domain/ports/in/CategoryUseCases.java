package top.dev.narvaez.product_inventory.products.domain.ports.in;

import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;

import java.util.List;

public interface CategoryUseCases {

    CategoryModel saveCategory(CategoryModel categoryModel);

    CategoryModel updateCategory(CategoryModel categoryModel, Long categoryId);

    CategoryModel findCategoryById(Long categoryId);

    CategoryModel findCategoryByName(String name);

    List<CategoryModel> findAllCategories();

}