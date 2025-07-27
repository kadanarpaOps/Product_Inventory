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
public class ReqProductDTO {

    private String name;
    private String description;
    private BigDecimal price;

}
