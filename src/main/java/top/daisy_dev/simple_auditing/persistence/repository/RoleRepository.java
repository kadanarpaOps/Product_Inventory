package top.daisy_dev.simple_auditing.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.daisy_dev.simple_auditing.persistence.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
