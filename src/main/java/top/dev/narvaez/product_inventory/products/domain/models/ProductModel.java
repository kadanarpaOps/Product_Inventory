package top.dev.narvaez.product_inventory.products.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {

    private Long id;
    private String name;
    private String description;
    private CategoryModel category;
    private BigDecimal price;
    private String manufacturer;
    private Integer stock;
    private Integer minStock;
    private Integer maxStock;
    private boolean active;
    private String imageUrl;
    private List<String> documentUrl;

}