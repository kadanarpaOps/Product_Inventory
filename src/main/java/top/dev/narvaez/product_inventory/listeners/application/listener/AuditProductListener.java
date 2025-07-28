package top.dev.narvaez.product_inventory.listeners.application.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.application.util.ProvisionalConstants;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.entity.AuditProductEntity;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.repository.JpaAuditProductRepository;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository.JpaProductRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuditProductListener {

    private final JpaAuditProductRepository auditProductRepo;
    private final JpaProductRepository productRepo;

    @PrePersist
    private void prePersist(ProductEntity productEntity) {
        AuditProductEntity auditProductEntity = fillDataIn(productEntity);
        auditProductEntity.setOperation("INSERT");
        this.auditProductRepo.save(auditProductEntity);
    }

    @PreUpdate
    private void preUpdate(ProductEntity productEntity) {
        AuditProductEntity auditProductEntity = fillDataIn(productEntity);
        auditProductEntity.setOperation("UPDATE");
        this.auditProductRepo.save(auditProductEntity);
    }

    @PreRemove
    private void preRemove(ProductEntity productEntity) {
        AuditProductEntity auditProductEntity = fillDataIn(productEntity);
        auditProductEntity.setOperation("DELETE");
        this.auditProductRepo.delete(auditProductEntity);
    }

    private AuditProductEntity fillDataIn(ProductEntity productEntity) {
        return AuditProductEntity.builder()
                .productId(
                        productEntity.getId() != null ? productRepo.findById(productEntity.getId()).get() : null)
                .newName(productEntity.getName())
                .newDescription(productEntity.getDescription())
                .newPrice(productEntity.getPrice())
                .newStock(productEntity.getStock())
                .auditUser(SecurityContextHolder.getContext().getAuthentication() == null ? ProvisionalConstants.AUDIT_USER : SecurityContextHolder.getContext().getAuthentication().getName())
                .auditDate(LocalDateTime.now())
                .build();
    }

}