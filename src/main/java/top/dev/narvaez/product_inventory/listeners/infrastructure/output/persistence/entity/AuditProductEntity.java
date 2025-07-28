package top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "AUDIT_PRODUCTS")
public class AuditProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = true)
    private ProductEntity productId;
    @Column(name = "OLD_PRODUCT_NAME")
    private String oldName;
    @Column(name = "NEW_PRODUCT_NAME")
    private String newName;
    @Column(name = "OLD_PRODUCT_DESCRIPTION")
    private String oldDescription;
    @Column(name = "NEW_PRODUCT_DESCRIPTION")
    private String newDescription;
    @Column(name = "OLD_PRODUCT_PRICE")
    private BigDecimal oldPrice;
    @Column(name = "NEW_PRODUCT_PRICE")
    private BigDecimal newPrice;
    @Column(name = "OLD_PRODUCT_STOCK")
    private Integer oldStock;
    @Column(name = "NEW_PRODUCT_STOCK")
    private Integer newStock;

    // Propiedades de Auditoría
    @Column(name = "OPERATION")
    private String operation;
    @Column(name = "AUDIT_USER")
    private String auditUser;
    @Column(name = "AUDIT_DATE")
    @DateTimeFormat(pattern = "dd.MM.yyy HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime auditDate;

}