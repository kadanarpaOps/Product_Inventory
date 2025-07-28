package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private String manufacturer;
    private Integer stock;
    private Integer minStock;
    private Integer maxStock;

}