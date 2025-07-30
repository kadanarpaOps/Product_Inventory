package top.dev.narvaez.product_inventory.products.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryModel {

    private Long id;
    private ProductCategory name;
    private String description;

}