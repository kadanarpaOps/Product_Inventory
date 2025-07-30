package top.dev.narvaez.product_inventory.listeners.infrastructure.input.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dev.narvaez.product_inventory.listeners.domain.ports.in.AuditProductUseCases;
import top.dev.narvaez.product_inventory.listeners.infrastructure.input.rest.dto.ResAuditProductDTO;
import top.dev.narvaez.product_inventory.listeners.infrastructure.input.rest.mapper.AuditProductRestMapper;

import java.util.List;

@RestController
@RequestMapping("/api/product/history")
@AllArgsConstructor
public class AuditProductRestController {

    private AuditProductUseCases auditProductService;

    private AuditProductRestMapper mapper;

    @GetMapping("/find-all")
    ResponseEntity<List<ResAuditProductDTO>> findAll() {
        return ResponseEntity.ok(auditProductService.findAllAuditProducts().stream().map(mapper::toResDTO).toList());
    }

    @GetMapping("/find/{id}")
    ResponseEntity<List<ResAuditProductDTO>> findAllByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(auditProductService.findAllAuditProductsByProductId(id).stream().map(mapper::toResDTO).toList());
    }

}
