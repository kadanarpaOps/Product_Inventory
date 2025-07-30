package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.ports.in.ProductUseCases;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.ProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.UpdateProductDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.mapper.ProductRestMapper;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductUseCases productService;

    private final ProductRestMapper mapper;

    @PostMapping("/create")
    ResponseEntity<UpdateProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.saveProduct(mapper.toModel(productDTO))));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<UpdateProductDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO productDTO) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.updateProduct(mapper.toModel(productDTO), id)));
    }

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

    @GetMapping("/find/search")
    ResponseEntity<List<UpdateProductDTO>> retrieveProductsByCustomSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Integer minStock,
            @RequestParam(required = false) Integer maxStock,
            @RequestParam(required = false) Boolean active
    ) {
        return ResponseEntity.ok(productService.findProductsByCustomSearch(
                name, category, minPrice, maxPrice, manufacturer, stock, minStock, maxStock, active
        ).stream().map(mapper::toUpdateDTO).toList());
    }

    @GetMapping("/available/find/{id}")
    ResponseEntity<UpdateProductDTO> retrieveAvailableProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toUpdateDTO(productService.findAvailableProductById(id)));
    }

    @GetMapping("/available/find-by-name")
    ResponseEntity<UpdateProductDTO> retrieveAvailableProductByName(@RequestParam String name) {
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

    @PutMapping("/active/{id}")
    ResponseEntity<Boolean> activeProduct(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(productService.activateProductById(id));
    }

    @PutMapping("/disable/{id}")
    ResponseEntity<Boolean> disableProduct(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(productService.disableProductById(id));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getRoles() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(role -> role.getAuthority()).toList());
    }

}