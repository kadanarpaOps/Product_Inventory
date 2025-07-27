package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value =
            "SELECT p FROM ProductEntity p WHERE p.active = TRUE"
    )
    List<ProductEntity> findAllAvailable();

    @Query(value =
            "SELECT p FROM ProductEntity p WHERE p.active = FALSE"
    )
    List<ProductEntity> findAllDisabled();

    @Query(value =
            "SELECT p FROM ProductEntity p WHERE p.id = :id AND p.active = TRUE"
    )
    List<ProductEntity> findAvailableById(@Param("id") Long id);

    @Query(value =
            "SELECT p FROM ProductEntity p WHERE p.name = :name AND p.active = TRUE"
    )
    List<ProductEntity> findAvailableByName(@Param("name") Long name);

    @Query(value =
            "SELECT p FROM ProductEntity p " +
                    "JOIN p.category c " +
                    "WHERE (:name IS NULL OR p.name ILIKE %:name%) " +
                    "AND (:category IS NULL OR c.name = :category) " +
                    "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
                    "AND (:maxPrice IS NULL or p.price <= :maxPrice) " +
                    "AND (:manufacturer IS NULL OR p.manufacturer ILIKE %:manufacturer%) " +
                    "AND (:stock IS NULL OR p.stock = :stock) " +
                    "AND (:minStock IS NULL OR p.minStock = :minStock) " +
                    "AND (:maxStock IS NULL OR p.maxStock = :maxStock) " +
                    "AND (:active IS NULL OR p.active = :active)"
    )
    List<ProductEntity> findFilteredProducts(
            @Param("name") String name,
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("manufacturer") String manufacturer,
            @Param("stock") Integer stock,
            @Param("minStock") Integer minStock,
            @Param("maxStock") Integer maxStock,
            @Param("active") boolean active
    );

}