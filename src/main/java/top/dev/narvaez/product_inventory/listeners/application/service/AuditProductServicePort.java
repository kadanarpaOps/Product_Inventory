package top.dev.narvaez.product_inventory.listeners.application.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;
import top.dev.narvaez.product_inventory.listeners.domain.model.OperationType;
import top.dev.narvaez.product_inventory.listeners.domain.ports.in.AuditProductUseCases;
import top.dev.narvaez.product_inventory.listeners.domain.ports.out.AuditProductRepositoryPort;
import top.dev.narvaez.product_inventory.users.application.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class AuditProductServicePort implements AuditProductUseCases {

    private final AuditProductRepositoryPort auditProductRepository;

    private final UserService userService;

    private final EntityManager entityManager;

    @Override
    public void saveAuditProduct(AuditProductModel auditProductModel) {
        auditProductRepository.saveAudit(auditProductModel);
    }

    @Override
    public List<AuditProductModel> findAllAuditProducts() {
        return auditProductRepository.selectAll();
    }

    @Override
    public List<AuditProductModel> findAllAuditProductsByProductId(Long productId) {
        return auditProductRepository.selectAllByProductId(productId);
    }

    @Transactional
    public void setAuditUpdateParams() {
        String username = userService.getAuthenticatedUser();
        entityManager.createNativeQuery("SET @operation = :op, @audit_user = :user, @audit_event = :date")
                .setParameter("op", OperationType.UPDATE)
                .setParameter("user", username)
                .setParameter("date", LocalDateTime.now())
                .executeUpdate();
    }
}