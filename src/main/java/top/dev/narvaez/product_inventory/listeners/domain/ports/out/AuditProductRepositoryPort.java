package top.dev.narvaez.product_inventory.listeners.domain.ports.out;

import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;

import java.util.List;

public interface AuditProductRepositoryPort {

    AuditProductModel saveAudit(AuditProductModel auditProductModel);

    List<AuditProductModel> selectAll();

    List<AuditProductModel> selectAllByProductId(Long productId);

}