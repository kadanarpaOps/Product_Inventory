package top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;
import top.dev.narvaez.product_inventory.listeners.domain.ports.out.AuditProductRepositoryPort;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.mapper.AuditProductPersistenceMapper;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.repository.JpaAuditProductRepository;

import java.util.List;

@AllArgsConstructor
@Component
public class AuditProducAdapterPort implements AuditProductRepositoryPort {

    private final JpaAuditProductRepository auditRepository;

    private final AuditProductPersistenceMapper mapper;

    @Override
    public AuditProductModel saveAudit(AuditProductModel auditProductModel) {
        return mapper.toModel(auditRepository.save(mapper.toEntity(auditProductModel)));
    }

    @Override
    public List<AuditProductModel> selectAll() {
        return auditRepository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public List<AuditProductModel> selectAllByProductId(Long productId) {
        return auditRepository.findByProductId(productId).stream().map(mapper::toModel).toList();
    }
}
