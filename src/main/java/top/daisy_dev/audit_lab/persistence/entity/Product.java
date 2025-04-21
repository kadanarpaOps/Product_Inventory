package top.daisy_dev.audit_lab.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    // Propiedades de Auditoría
    private String operation;
    @Column(name = "date_event")
    @DateTimeFormat(pattern = "dd.MM.yyy HH:mm:ss")
    private LocalDateTime dateEvent;

    public void audit(String operation) {
        this.setOperation(operation);
        this.setDateEvent(LocalDateTime.now());
    }

    @PrePersist
    public void onPrePersist() {
        this.audit("INSERT");
    }

    @PreUpdate
    public void onPreUpdate() {
        this.audit("UPDATE");
    }

}
