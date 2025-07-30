package top.dev.narvaez.product_inventory.products.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.ports.in.CategoryUseCases;
import top.dev.narvaez.product_inventory.products.domain.ports.out.CategoryRepositoryPort;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServicePort implements CategoryUseCases {

    private final CategoryRepositoryPort categoryRepository;

    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        return categoryRepository.saveCategory(categoryModel);
    }

    @Override
    public CategoryModel updateCategory(CategoryModel categoryModel, Long categoryId) {
        if (categoryModel.getId() == null) categoryModel.setId(categoryId);
        if (!categoryModel.getId().equals(categoryId))
            throw new IllegalArgumentException("The REST category id does not match the BODY category id");
        CategoryModel categoryFromEntity = findCategoryById(categoryId);
        categoryFromEntity.setName(categoryModel.getName());
        categoryFromEntity.setDescription(categoryModel.getDescription());
        return categoryRepository.saveCategory(categoryFromEntity);
    }

    @Override
    public CategoryModel findCategoryById(Long categoryId) {
        return categoryRepository.selectById(categoryId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CategoryModel findCategoryByName(String name) {
        return categoryRepository.selectByName(ProductCategory.valueOf(name))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<CategoryModel> findAllCategories() {
        return categoryRepository.selectAll();
    }

}