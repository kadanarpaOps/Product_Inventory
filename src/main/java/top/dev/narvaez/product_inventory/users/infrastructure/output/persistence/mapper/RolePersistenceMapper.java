package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.RoleEntity;

@Component
public class RolePersistenceMapper extends MapperStructure {

    public RoleEntity toEntity(RoleModel roleModel) {
        return mapper.map(roleModel, RoleEntity.class);
    }

    public RoleModel toModel(RoleEntity roleEntity) {
        return mapper.map(roleEntity, RoleModel.class);
    }

}