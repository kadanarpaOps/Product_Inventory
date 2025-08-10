package top.dev.narvaez.product_inventory.users.domain.ports.in;

import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;

import java.util.List;

public interface RoleUseCases {

    RoleModel saveRole(RoleModel role);

    RoleModel findAnyRoleById(Long id);

    RoleModel findAnyRoleByName(RoleName name);

    List<RoleModel> findAllRoles();

}