package top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.dev.narvaez.product_inventory.listeners.infrastructure.output.persistence.entity.AuditProductEntity;

import java.util.List;

@Repository
public interface JpaAuditProductRepository extends JpaRepository<AuditProductEntity, Long> {

    @Query(value =
            "SELECT aud FROM AuditProductEntity aud " +
                    "JOIN aud.productId p " +
                    "WHERE p.id = :productId"
    )
    List<AuditProductEntity> findByProductId(@Param("productId") Long productId);

}