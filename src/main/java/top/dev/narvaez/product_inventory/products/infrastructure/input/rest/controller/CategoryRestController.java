package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dev.narvaez.product_inventory.products.domain.ports.in.CategoryUseCases;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.dto.CategoryDTO;
import top.dev.narvaez.product_inventory.products.infrastructure.input.rest.mapper.CategoryRestMapper;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryRestController {

    private final CategoryUseCases categoryService;

    private final CategoryRestMapper mapper;

    @GetMapping("/find-all")
    ResponseEntity<List<CategoryDTO>> retrieveAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories()
                .stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/find/{id}")
    ResponseEntity<CategoryDTO> retrieveCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(categoryService.findCategoryById(id)));
    }

    @GetMapping("/find-by-name/{name}")
    ResponseEntity<CategoryDTO> retrieveCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(mapper.toDTO(categoryService.findCategoryByName(name)));
    }

}