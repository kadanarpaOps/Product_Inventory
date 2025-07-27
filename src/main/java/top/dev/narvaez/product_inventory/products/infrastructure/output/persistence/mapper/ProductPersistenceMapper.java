package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;

@Component
public class ProductPersistenceMapper extends MapperStructure {

    public ProductEntity toEntity(ProductModel productModel) {
        return mapper.map(productModel, ProductEntity.class);
    }

    public ProductModel toModel(ProductEntity productEntity) {
        return mapper.map(productEntity, ProductModel.class);
    }

}