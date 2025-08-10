package top.dev.narvaez.product_inventory.users.infrastructure.input.rest.mapper;

import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;
import top.dev.narvaez.product_inventory.users.domain.models.UserModel;
import top.dev.narvaez.product_inventory.users.infrastructure.input.rest.dto.PutUserDTO;
import top.dev.narvaez.product_inventory.users.infrastructure.input.rest.dto.ReqUserDTO;
import top.dev.narvaez.product_inventory.users.infrastructure.input.rest.dto.ResUserDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRestMapper extends MapperStructure {

    public UserRestMapper() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<List<RoleName>, List<RoleModel>> setRoleModelList = r -> {
            List<RoleModel> roleModels = new ArrayList<>();
            r.getSource().stream().forEach(roleName -> {
                roleModels.add(new RoleModel(null, roleName));
            });
            return roleModels;
        };

        Converter<List<RoleModel>, List<RoleName>> setRoleNameList = r -> {
            List<RoleName> roleNames = new ArrayList<>();
            r.getSource().stream().forEach(roleName -> {
                roleNames.add(roleName.getName());
            });
            return roleNames;
        };

        mapper.typeMap(ReqUserDTO.class, UserModel.class)
                .addMappings(m -> m.using(setRoleModelList).map(
                        ReqUserDTO::getRoles, UserModel::setRoles
                ));

        mapper.typeMap(PutUserDTO.class, UserModel.class)
                .addMappings(m -> m.using(setRoleModelList).map(
                        PutUserDTO::getRoles, UserModel::setRoles
                ));

        mapper.typeMap(UserModel.class, ResUserDTO.class)
                .addMappings(m -> m.using(setRoleNameList).map(
                        UserModel::getRoles, ResUserDTO::setRoles
                ));

    }

    public ResUserDTO toDTO(UserModel userModel) {
        return mapper.map(userModel, ResUserDTO.class);
    }

    public UserModel toModel(ReqUserDTO reqUserDTO) {
        return mapper.map(reqUserDTO, UserModel.class);
    }

}