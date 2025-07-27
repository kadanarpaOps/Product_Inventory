package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.dev.narvaez.product_inventory.products.domain.ports.in.ProductUseCases;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.ProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository.JpaProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductUseCases producteService;

    @GetMapping("/find-all")
    ResponseEntity<List<ProductDTO>> retrieveAllProducts() {
        return null;
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

}
