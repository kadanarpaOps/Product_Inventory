package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.RoleEntity;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "SELECT r FROM RoleEntity r WHERE r.name = :name")
    public Optional<RoleEntity> findRoleByName(@Param("name") String name);

}
