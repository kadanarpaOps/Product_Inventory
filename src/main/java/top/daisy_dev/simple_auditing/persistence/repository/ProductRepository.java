package top.daisy_dev.simple_auditing.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.daisy_dev.simple_auditing.persistence.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}