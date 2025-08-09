package top.dev.narvaez.product_inventory.users.application.service.roles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.common.model.exceptions.EntityNotFoundException;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;
import top.dev.narvaez.product_inventory.users.domain.ports.in.RoleUseCases;
import top.dev.narvaez.product_inventory.users.domain.ports.out.RoleRepositoryPort;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleServicePort implements RoleUseCases {

    private final RoleRepositoryPort roleRepository;

    @Override
    public RoleModel saveRole(RoleModel role) {
        return null;
    }

    @Override
    public RoleModel findAnyRoleById(Long id) {
        return null;
    }

    @Override
    public RoleModel findAnyRoleByName(RoleName name) {
        return roleRepository.selectByName(name)
                .orElseThrow(() -> new EntityNotFoundException(Constants.ROLE_ENTITY, Constants.NAME, name.name()));
    }

    @Override
    public List<RoleModel> findAllRoles() {
        return List.of();
    }
}
