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
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.UpdateProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.mapper.ProductRestMapper;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.entity.ProductEntity;
import top.dev.narvaez.product_inventory.products.infrastructure.output.persistence.repository.JpaProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductUseCases productService;

    private final ProductRestMapper mapper;

    @GetMapping("/find/{id}")
    ResponseEntity<UpdateProductDTO> retrieveAnyProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.findAnyProductById(id)));
    }

    @GetMapping("/find-by-name")
    ResponseEntity<UpdateProductDTO> retrieveAnyProductByName(@RequestParam String name) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.findAnyProductByName(name)));
    }

    @GetMapping("/find-all")
    ResponseEntity<List<UpdateProductDTO>> retrieveAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts()
                .stream().map(mapper::toUpdateDTO).toList());
    }

    @GetMapping("/available/find/{id}")
    ResponseEntity<UpdateProductDTO> retrieveAvailableProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.findAvailableProductById(id)));
    }

    @GetMapping("/available/find-by-name/{name}")
    ResponseEntity<UpdateProductDTO> retrieveAvailableProductByName(@PathVariable String name) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.findAvailableProductByName(name)));
    }

    @GetMapping("/available/find-all")
    ResponseEntity<List<UpdateProductDTO>> retrieveAvailableProducts() {
        return ResponseEntity.ok((productService.findAllAvailableProducts()
                .stream().map(mapper::toUpdateDTO).toList()));
    }

    @GetMapping("/disabled/find-all")
    ResponseEntity<List<UpdateProductDTO>> retrieveDisabledProducts() {
        return ResponseEntity.ok((productService.findAllDisabledProducts()
                .stream().map(mapper::toUpdateDTO).toList()));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

}
