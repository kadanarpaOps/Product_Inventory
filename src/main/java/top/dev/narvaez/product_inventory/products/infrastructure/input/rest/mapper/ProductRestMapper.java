package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.mapper;

import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.ProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.UpdateProductDTO;

@Component
public class ProductRestMapper extends MapperStructure {

    /**
     * Así le decís a ModelMapper: “para mapear esto, usá este bloque de lógica” sin violar su estructura.
     * Creamos un {@code Converter<String, CategoryModel>} que transforma el String del DTO en una {@code CategoryModel} válida.
     * Luego, lo aplicamos sobre el typeMap, mapeando el atributo category como corresponde.
     */
    public ProductRestMapper() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<String, CategoryModel> setCategoryModelName = c -> {
            if (c.getSource() == null) return null;
            return new CategoryModel(null, ProductCategory.valueOf(c.getSource().toString()), null);
        };

        Converter<CategoryModel, String> setCategoryDto = c -> {
            return c.getSource().getName().name();
        };

        mapper.typeMap(ProductDTO.class, ProductModel.class)
                .addMappings(m -> m.using(setCategoryModelName).map(
                        ProductDTO::getCategory, ProductModel::setCategory
                ));

        mapper.typeMap(ProductModel.class, UpdateProductDTO.class)
                .addMappings(m -> m.using(setCategoryDto).map(
                        ProductModel::getCategory, UpdateProductDTO::setCategory
                ));

    }

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