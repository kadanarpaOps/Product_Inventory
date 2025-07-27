package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.CategoryEntity;

@Component
public class CategoryPersistenceMapper extends MapperStructure {

    public CategoryEntity toEntity(CategoryModel categoryModel) {
        return mapper.map(categoryModel, CategoryEntity.class);
    }

    public CategoryModel toModel(CategoryEntity categoryEntity) {
        return mapper.map(categoryEntity, CategoryModel.class);
    }

}
