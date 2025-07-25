package top.daisy_dev.simple_auditing.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.daisy_dev.simple_auditing.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username", nativeQuery = false)
    public Optional<UserEntity> findUser(@Param("username") String username);

}