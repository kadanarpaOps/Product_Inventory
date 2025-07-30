package top.dev.narvaez.product_inventory.listeners.domain.ports.in;

import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;

import java.util.List;

public interface AuditProductUseCases {

    void saveAuditProduct(AuditProductModel auditProductModel);

    List<AuditProductModel> findAllAuditProducts();

    List<AuditProductModel> findAllAuditProductsByProductId(Long productId);

    void setAuditUpdateParams();

}