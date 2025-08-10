package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.ports.out.CategoryRepositoryPort;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.mapper.CategoryPersistenceMapper;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository.JpaCategoryRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class CategoryAdapterPort implements CategoryRepositoryPort {

    private final JpaCategoryRepository categoryRepository;

    private final CategoryPersistenceMapper mapper;

    @Override
    public CategoryModel saveCategory(CategoryModel category) {
        return mapper.toModel(categoryRepository.save(mapper.toEntity(category)));
    }

    @Override
    public Optional<CategoryModel> selectByName(ProductCategory category) {
        return categoryRepository.findByName(category).map(mapper::toModel);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryRepository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<CategoryModel> selectById(Long id) {
        return categoryRepository.findById(id).map(mapper::toModel);
    }

    public boolean verifyRepositoryReady() {
        return categoryRepository.count() == 0;
    }
}
