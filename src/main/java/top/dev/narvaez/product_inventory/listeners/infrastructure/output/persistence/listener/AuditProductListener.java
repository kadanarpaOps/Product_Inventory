package top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PreRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;
import top.dev.narvaez.product_inventory.listeners.domain.model.OperationType;
import top.dev.narvaez.product_inventory.listeners.domain.ports.in.AuditProductUseCases;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.mapper.ProductPersistenceMapper;
import top.dev.narvaez.product_inventory.users.application.service.UserService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuditProductListener {

    private final AuditProductUseCases auditProductService;

    private final ProductPersistenceMapper mapper;

    private final UserService userService;

    @PostPersist
    private void prePersist(ProductEntity productEntity) {
        AuditProductModel auditProduct = fillDataIn(productEntity);
        auditProduct.setOperation(OperationType.INSERT);
        this.auditProductService.saveAuditProduct(auditProduct);
    }

    @PreRemove
    private void preRemove(ProductEntity productEntity) {
        AuditProductModel auditProduct = fillDataIn(productEntity);
        auditProduct.setOperation(OperationType.DELETE);
        this.auditProductService.saveAuditProduct(auditProduct);
    }

    private AuditProductModel fillDataIn(ProductEntity productEntity) {
        ProductModel productModel = mapper.toModel(productEntity);
        return AuditProductModel.builder()
                .productId(productModel)
                .newName(productModel.getName())
                .newDescription(productModel.getDescription())
                .newPrice(productModel.getPrice())
                .newStock(productModel.getStock())
                .auditUser(userService.getAuthenticatedUser())
                .auditDate(LocalDateTime.now())
                .build();
    }

}