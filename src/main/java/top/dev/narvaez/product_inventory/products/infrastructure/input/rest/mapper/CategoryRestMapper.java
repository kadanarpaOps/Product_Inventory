package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.CategoryDTO;

@Component
public class CategoryRestMapper extends MapperStructure {

    public CategoryDTO toDTO(CategoryModel categoryModel) {
        return mapper.map(categoryModel, CategoryDTO.class);
    }

}
