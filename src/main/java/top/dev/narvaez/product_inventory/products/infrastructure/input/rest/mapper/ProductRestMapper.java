package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.ProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.UpdateProductDTO;

@Component
public class ProductRestMapper extends MapperStructure {

    public ProductDTO toDTO(ProductModel productModel) {
        return mapper.map(productModel, ProductDTO.class);
    }

    public UpdateProductDTO toUpdateDTO(ProductModel productModel) {
        return mapper.map(productModel, UpdateProductDTO.class);
    }

    public ProductModel toModel(ProductDTO productDTO) {
        return mapper.map(productDTO, ProductModel.class);
    }

}
