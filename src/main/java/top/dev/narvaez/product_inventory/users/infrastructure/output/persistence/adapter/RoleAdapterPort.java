package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;
import top.dev.narvaez.product_inventory.users.domain.ports.out.RoleRepositoryPort;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.mapper.RolePersistenceMapper;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.repository.JpaRoleRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class RoleAdapterPort implements RoleRepositoryPort {
    
    private final JpaRoleRepository roleRepository;
    
    private final RolePersistenceMapper mapper;
    
    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        return mapper.toModel(roleRepository.save(mapper.toEntity(roleModel)));
    }

    @Override
    public List<RoleModel> selectAll() {
        return roleRepository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<RoleModel> selectById(Long id) {
        return roleRepository.findById(id).map(mapper::toModel);
    }

    @Override
    public Optional<RoleModel> selectByName(RoleName roleName) {
        return roleRepository.findRoleByName(roleName.name()).map(mapper::toModel);
    }
}
