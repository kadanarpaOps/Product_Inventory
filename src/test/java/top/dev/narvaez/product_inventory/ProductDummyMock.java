package top.dev.narvaez.product_inventory;

import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;

import java.math.BigDecimal;
import java.util.List;


public class ProductDummyMock {


    public List<CategoryModel> mapAnyCategoryModelList() {
        return List.of(
                createCategoryModel(1L, ProductCategory.FRUIT, "fruit")
        );
    }

    public CategoryModel mapCategoryModel() {
        return createCategoryModel(12L, ProductCategory.ELECTRONICS, "electronics");
    }

    public CategoryModel mapUndefinedCategoryModel() {
        return createCategoryModel(15L, ProductCategory.UNDEFINED, "undefined");
    }

    public CategoryModel createCategoryModel(Long id, ProductCategory name, String description) {
        return CategoryModel.builder().id(id).name(name).description(description).build();
    }

    public ProductModel mapNullValuesProductModel() {
        return createProductModel(
                null,
                "Lenovo",
                "Laptop",
                null,
                BigDecimal.valueOf(2000000),
                null,
                null,
                null,
                null,
                false
        );
    }

    public ProductModel mapNotNullValuesProductModel() {
        return createProductModel(1L,
                "Strawberries",
                "Fruit",
                mapCategoryModel(),
                BigDecimal.valueOf(0),
                "El Fruver",
                20,
                5,
                25,
                true
        );
    }

    public ProductModel createProductModel(
            Long id,
            String name,
            String description,
            CategoryModel category,
            BigDecimal price,
            String manufacturer,
            Integer stock,
            Integer minStock,
            Integer maxStock,
            boolean active) {
        return ProductModel.builder()
                .id(id)
                .name(name)
                .description(description)
                .category(category)
                .price(price)
                .manufacturer(manufacturer)
                .stock(stock)
                .minStock(minStock)
                .maxStock(maxStock)
                .active(active)
                .build();
    }

}