package top.dev.narvaez.product_inventory.listeners.infrastructure.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResAuditProductDTO {

    private Long id;
    private Long productId;
    private String oldName;
    private String newName;
    private String oldDescription;
    private String newDescription;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Integer oldStock;
    private Integer newStock;

    private String operation;
    private String auditUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime auditDate;

}
