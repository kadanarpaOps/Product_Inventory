package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.UserEntity;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
    public Optional<UserEntity> findUserByUsername(@Param("username") String username);

}