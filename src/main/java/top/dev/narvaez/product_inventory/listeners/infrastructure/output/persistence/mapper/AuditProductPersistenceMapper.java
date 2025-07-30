package top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.entity.AuditProductEntity;

@Component
public class AuditProductPersistenceMapper extends MapperStructure {

    public AuditProductEntity toEntity(AuditProductModel auditProductModel) {
        return mapper.map(auditProductModel, AuditProductEntity.class);
    }

    public AuditProductModel toModel(AuditProductEntity auditProductEntity) {
        return mapper.map(auditProductEntity, AuditProductModel.class);
    }

}
