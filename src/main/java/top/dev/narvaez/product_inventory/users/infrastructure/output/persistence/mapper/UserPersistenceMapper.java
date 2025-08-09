package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.mapper;

import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.users.domain.models.UserModel;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.UserEntity;

@Component
public class UserPersistenceMapper extends MapperStructure {

    public UserEntity toEntity(UserModel userModel) {
        return mapper.map(userModel, UserEntity.class);
    }

    public UserModel toModel(UserEntity userEntity) {
        return mapper.map(userEntity, UserModel.class);
    }

}