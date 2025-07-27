package top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}