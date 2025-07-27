package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import top.dev.narvaez.product_inventory.listeners.application.listener.AuditProductListener;

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
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;

}
