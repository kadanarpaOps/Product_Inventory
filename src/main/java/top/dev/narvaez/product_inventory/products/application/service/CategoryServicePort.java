package top.dev.narvaez.product_inventory.products.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.common.model.exceptions.EntityNotFoundException;
import top.dev.narvaez.product_inventory.common.model.exceptions.MismatchedModelIdException;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.ports.in.CategoryUseCases;
import top.dev.narvaez.product_inventory.products.domain.ports.out.CategoryRepositoryPort;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.CategoryEntity;

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
            throw new MismatchedModelIdException(categoryId, categoryModel.getId(), CategoryModel.class.getSimpleName());
        CategoryModel categoryFromEntity = findCategoryById(categoryId);
        categoryFromEntity.setName(categoryModel.getName());
        categoryFromEntity.setDescription(categoryModel.getDescription());
        return categoryRepository.saveCategory(categoryFromEntity);
    }

    @Override
    public CategoryModel findCategoryById(Long categoryId) {
        return categoryRepository.selectById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(CategoryEntity.class.getSimpleName(), Constants.ID, categoryId.toString()));
    }

    @Override
    public CategoryModel findCategoryByName(String name) {
        return categoryRepository.selectByName(ProductCategory.valueOf(name))
                .orElseThrow(() -> new EntityNotFoundException(CategoryEntity.class.getSimpleName(), Constants.NAME, name));
    }

    @Override
    public List<CategoryModel> findAllCategories() {
        return categoryRepository.selectAll();
    }

}