package top.daisy_dev.audit_lab.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.daisy_dev.audit_lab.persistence.entity.Product;
import top.daisy_dev.audit_lab.persistence.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepo;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.save(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Product updatedProduct = productRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());

        return ResponseEntity.ok(productRepo.save(updatedProduct));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        if (!productRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        productRepo.deleteById(id);
        return ResponseEntity.ok(true);
    }

}
