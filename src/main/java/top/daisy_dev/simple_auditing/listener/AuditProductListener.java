package top.daisy_dev.simple_auditing.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.daisy_dev.simple_auditing.persistence.entity.AuditProduct;
import top.daisy_dev.simple_auditing.persistence.entity.Product;
import top.daisy_dev.simple_auditing.persistence.repository.AuditProductRepository;
import top.daisy_dev.simple_auditing.persistence.repository.ProductRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuditProductListener {

    private final AuditProductRepository auditProductRepo;
    private final ProductRepository productRepo;

    @PrePersist
    private void prePersist(Product product) {
        AuditProduct auditProduct = fillDataIn(product);
        auditProduct.setOperation("INSERT");
        this.auditProductRepo.save(auditProduct);
    }

    @PreUpdate
    private void preUpdate(Product product) {
        AuditProduct auditProduct = fillDataIn(product);
        auditProduct.setOperation("UPDATE");
        this.auditProductRepo.save(auditProduct);
    }

    @PreRemove
    private void preRemove(Product product) {
        AuditProduct auditProduct = fillDataIn(product);
        auditProduct.setOperation("DELETE");
        this.auditProductRepo.delete(auditProduct);
    }

    private AuditProduct fillDataIn(Product product) {
        return AuditProduct.builder()
                .productId(product.getId())
                .oldName(product.getId() == null || product.getId().toString().isEmpty() ? null : productRepo.findById(product.getId()).get().getName())
                .newName(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .auditUser(SecurityContextHolder.getContext().getAuthentication().getName())
                .auditDate(LocalDateTime.now())
                .build();
    }

}