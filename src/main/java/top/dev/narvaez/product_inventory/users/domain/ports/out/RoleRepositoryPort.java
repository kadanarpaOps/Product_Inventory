package top.dev.narvaez.product_inventory.users.domain.ports.out;

import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {

    RoleModel saveRole(RoleModel role);

    List<RoleModel> selectAll();

    Optional<RoleModel> selectById(Long id);

    Optional<RoleModel> selectByName(RoleName roleName);

}
