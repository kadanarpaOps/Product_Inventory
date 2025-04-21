package top.daisy_dev.audit_lab.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.daisy_dev.audit_lab.persistence.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}