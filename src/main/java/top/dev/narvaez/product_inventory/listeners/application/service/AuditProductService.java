package top.dev.narvaez.product_inventory.listeners.application.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuditProductService {

    private final EntityManager entityManager;

    @Transactional
    public void setAuditUserSession(String username) {
        entityManager.createNativeQuery("SET @audit_user = :user")
                .setParameter("user", username)
                .executeUpdate();
    }
}