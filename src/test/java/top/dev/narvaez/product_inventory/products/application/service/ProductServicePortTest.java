package top.dev.narvaez.product_inventory.products.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import top.dev.narvaez.product_inventory.ProductDummyMock;
import top.dev.narvaez.product_inventory.listeners.domain.ports.in.AuditProductUseCases;
import top.dev.narvaez.product_inventory.products.domain.ports.in.CategoryUseCases;
import top.dev.narvaez.product_inventory.products.domain.ports.out.ProductRepositoryPort;

@ExtendWith(MockitoExtension.class)
class ProductServicePortTest {

    @Mock
    ProductRepositoryPort productRepository;

    @Mock
    CategoryUseCases categoryService;

    @Mock
    AuditProductUseCases auditService;

    ProductDummyMock mockMapper;

    @BeforeEach
    void setUp() {
    mockMapper = new ProductDummyMock();
        mockMapper = new ProductDummyMock();
    }

}