package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.listener.AuditProductListener;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "PRODUCTS")
@EntityListeners(AuditProductListener.class)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal price;
    @Column(name = "PRODUCT_MANUFACTURER")
    private String manufacturer;
    @Column(name = "PRODUCT_STOCK")
    private Integer stock;
    @Column(name = "PRODUCT_MIN_STOCK")
    private Integer minStock;
    @Column(name = "PRODUCT_MAX_STOCK")
    private Integer maxStock;
    @Column(name = "PRODUCT_ACTIVE")
    private boolean active;

}