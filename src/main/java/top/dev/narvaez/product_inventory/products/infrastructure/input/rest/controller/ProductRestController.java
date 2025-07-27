package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.ReqProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository.JpaProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final JpaProductRepository productRepo;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody ReqProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.save(productEntity));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity productEntity, @PathVariable Long id) {
        ProductEntity updatedProductEntity = productRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        updatedProductEntity.setName(productEntity.getName());
        updatedProductEntity.setDescription(productEntity.getDescription());
        updatedProductEntity.setPrice(productEntity.getPrice());

        return ResponseEntity.ok(productRepo.save(updatedProductEntity));
    }

    @GetMapping("/find")
    public ResponseEntity<List<ProductEntity>> findAll() {
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
