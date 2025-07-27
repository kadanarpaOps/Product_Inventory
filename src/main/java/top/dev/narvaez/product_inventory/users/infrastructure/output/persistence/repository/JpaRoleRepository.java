package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.RoleEntity;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
}
