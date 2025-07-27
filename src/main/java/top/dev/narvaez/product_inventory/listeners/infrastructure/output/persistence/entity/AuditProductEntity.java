package top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "OLD_PRODUCT_NAME")
    private String oldName;
    @Column(name = "NEW_PRODUCT_NAME")
    private String newName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;

    // Propiedades de Auditoría
    @Column(name = "OPERATION")
    private String operation;
    @Column(name = "AUDIT_USER")
    private String auditUser;
    @Column(name = "AUDIT_DATE")
    @DateTimeFormat(pattern = "dd.MM.yyy HH:mm:ss", iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime auditDate;

}