package top.daisy_dev.simple_auditing.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.daisy_dev.simple_auditing.controller.dto.request.ReqProductDTO;
import top.daisy_dev.simple_auditing.persistence.entity.Product;
import top.daisy_dev.simple_auditing.persistence.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepo;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ReqProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.save(product));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Product updatedProduct = productRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());

        return ResponseEntity.ok(productRepo.save(updatedProduct));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        if (!productRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        productRepo.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

}
