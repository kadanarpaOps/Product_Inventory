package top.daisy_dev.simple_auditing.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.daisy_dev.simple_auditing.persistence.entity.AuditProduct;

@Repository
public interface AuditProductRepository extends JpaRepository<AuditProduct, Long> {

}