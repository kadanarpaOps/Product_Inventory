package top.dev.narvaez.product_inventory.products.domain.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryModel {

    private String id;
    @Enumerated(EnumType.STRING)
    private ProductCategory name;
    private String description;

}