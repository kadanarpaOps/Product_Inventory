package top.dev.narvaez.product_inventory.listeners.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuditProductModel {

    private Long id;
    private ProductModel productId;
    private String oldName;
    private String newName;
    private String oldDescription;
    private String newDescription;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Integer oldStock;
    private Integer newStock;

    private OperationType operation;
    private String auditUser;
    private LocalDateTime auditDate;

}
