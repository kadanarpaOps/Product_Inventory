package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.CategoryEntity;

import java.util.Optional;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(value =
    "SELECT c FROM CategoryEntity c WHERE c.name = :name"
    )
    Optional<CategoryEntity> findByName(@Param("name") String name);

}