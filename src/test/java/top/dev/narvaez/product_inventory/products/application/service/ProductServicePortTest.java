package top.dev.narvaez.product_inventory.products.application.service;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.listeners.domain.ports.in.AuditProductUseCases;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.domain.ports.in.CategoryUseCases;
import top.dev.narvaez.product_inventory.products.domain.ports.out.ProductRepositoryPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServicePortTest {

    @Mock
    ProductRepositoryPort productRepository;

    @Mock
    CategoryUseCases categoryService;

    @Mock
    AuditProductUseCases auditService;

    @InjectMocks
    ProductServicePort productService;

    ProductDummyMock mockMapper;

    private List<ProductModel> mockModelList;

    private ProductModel mockNullProduct;

    private ProductModel mockProduct;

    @BeforeEach
    void setUp() {
        mockMapper = new ProductDummyMock();
        mockNullProduct = mockMapper.mapNullValuesProductModel();
        mockProduct = mockMapper.mapNotNullValuesProductModel();
        mockModelList = List.of(mockNullProduct, mockProduct);
    }

    @Test
    @DisplayName("saveProduct - Should return the created Product Model")
    void saveProductShouldReturnTheCreatedProductModel() {
        ProductModel productModel = mockMapper.mapNullValuesProductModel();

        when(categoryService.findCategoryByName(anyString())).thenReturn(mockMapper.mapUndefinedCategoryModel());

        when(productRepository.saveProduct(any())).thenAnswer(invocationOnMock -> {
            ProductModel toSave = invocationOnMock.getArgument(0);
            toSave.setId(1L);
            return toSave;
        });

        ProductModel savedProduct = productService.saveProduct(productModel);

        assertNotNull(savedProduct.getId());
        assertNotNull(savedProduct.getCategory());
        assertNotNull(savedProduct.getManufacturer());
        assertNotNull(savedProduct.getStock());
        assertNotNull(savedProduct.getMinStock());
        assertNotNull(savedProduct.getMaxStock());
        assertFalse(savedProduct.isActive());
        assertEquals(savedProduct.getName(), productModel.getName());
        assertEquals(ProductCategory.UNDEFINED, savedProduct.getCategory().getName());
        assertEquals(Constants.MIN_STOCK, savedProduct.getStock());

        productService.saveProduct(productModel);

        verify(categoryService, times(2)).findCategoryByName(anyString());
        verify(productRepository, times(2)).saveProduct(any(ProductModel.class));
    }

    @Test
    @DisplayName("updateProduct - Should return the Updated Product")
    void updateProductShouldReturnTheUpdatedProduct() {

        ProductModel oldProduct = mockNullProduct;

        when(productRepository.selectById(anyLong())).thenReturn(Optional.of(oldProduct));

        when(productRepository.saveProduct(any(ProductModel.class))).thenAnswer(invocationOnMock -> {
            ProductModel toUpdate = invocationOnMock.getArgument(0);
            toUpdate.setName(mockProduct.getName());
            toUpdate.setDescription(mockProduct.getDescription());
            toUpdate.setCategory(mockProduct.getCategory());
            toUpdate.setPrice(mockProduct.getPrice());
            toUpdate.setManufacturer(mockProduct.getManufacturer());
            toUpdate.setStock(mockProduct.getStock());
            toUpdate.setMinStock(mockProduct.getMinStock());
            toUpdate.setMaxStock(mockProduct.getMaxStock());
            toUpdate.setActive(mockProduct.isActive());
            return toUpdate;
        });

        ProductModel updatedProduct = productService.updateProduct(mockProduct, 1L);

        assertNotNull(updatedProduct.getId());
        assertNotNull(updatedProduct.getCategory());
        assertNotNull(updatedProduct.getManufacturer());
        assertNotNull(updatedProduct.getStock());
        assertNotNull(updatedProduct.getMinStock());
        assertNotNull(updatedProduct.getMaxStock());
        assertEquals(mockProduct.getName(), updatedProduct.getName());
        assertEquals(ProductCategory.ELECTRONICS, updatedProduct.getCategory().getName());

        verify(productRepository, times(1)).saveProduct(any(ProductModel.class));

    }

    @Test
    @DisplayName("updateProduct - Should throws IllegalArgumentException")
    void updateProductShouldThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () ->
                productService.updateProduct(mockProduct, 10L));

    }

    @Test
    @DisplayName("select Methods - Should return Single Product Model")
    void selectProductShouldReturnSingleProductModel() {

        when(productRepository.selectAvailableById(anyLong())).thenReturn(Optional.of(mockProduct));
        when(productRepository.selectById(anyLong())).thenReturn(Optional.of(mockProduct));
        when(productRepository.selectAvailableByName(anyString())).thenReturn(Optional.of(mockProduct));
        when(productRepository.selectByName(anyString())).thenReturn(Optional.of(mockProduct));

        ProductModel availableById = productService.findAvailableProductById(1L);
        ProductModel byId = productService.findAnyProductById(1L);
        ProductModel availableByName = productService.findAvailableProductByName("Strawberries");
        ProductModel byName = productService.findAnyProductByName("Strawberries");

        assertNotNull(availableById);
        assertEquals(ProductCategory.ELECTRONICS, availableById.getCategory().getName());

        assertNotNull(byId);
        assertEquals(ProductCategory.ELECTRONICS, byId.getCategory().getName());

        assertNotNull(availableByName);
        assertEquals(ProductCategory.ELECTRONICS, availableByName.getCategory().getName());

        assertNotNull(byName);
        assertEquals(ProductCategory.ELECTRONICS, byName.getCategory().getName());

        verify(productRepository, times(1)).selectAvailableById(anyLong());
        verify(productRepository, times(1)).selectById(anyLong());
        verify(productRepository, times(1)).selectAvailableByName(anyString());
        verify(productRepository, times(1)).selectByName(anyString());

    }

    @Test
    @DisplayName("select Methods - Should return a List of Product Models")
    void shouldTestAllProductQueriesCorrectly() {

        when(productRepository.selectAllAvailable()).thenReturn(mockModelList);
        when(productRepository.selectAllDisabled()).thenReturn(mockModelList);
        when(productRepository.selectAll()).thenReturn(mockModelList);
        when(productRepository.selectByCustomSearch(
                "Laptop",
                ProductCategory.UNDEFINED,
                BigDecimal.valueOf(1000000),
                BigDecimal.valueOf(3000000),
                "Lenovo",
                null,
                null,
                null,
                false
        )).thenReturn(mockModelList);

        List<ProductModel> available = productService.findAllAvailableProducts();
        assertNotNull(available);
        assertEquals(2, available.size());
        assertEquals("Laptop", available.get(0).getDescription());

        List<ProductModel> disabled = productService.findAllDisabledProducts();
        assertNotNull(disabled);
        assertEquals(2, disabled.size());
        assertEquals("Laptop", disabled.get(0).getDescription());

        List<ProductModel> all = productService.findAllProducts();
        assertNotNull(all);
        assertEquals(2, all.size());
        assertEquals("Laptop", all.get(0).getDescription());

        List<ProductModel> filtered = productService.findProductsByCustomSearch(
                "Laptop",
                ProductCategory.UNDEFINED,
                BigDecimal.valueOf(1000000),
                BigDecimal.valueOf(3000000),
                "Lenovo",
                null,
                null,
                null,
                false
        );

        assertNotNull(filtered);
        assertEquals(2, filtered.size());
        assertEquals("Laptop", filtered.get(0).getDescription());

        verify(productRepository, times(1)).selectAllAvailable();
        verify(productRepository, times(1)).selectAllDisabled();
        verify(productRepository, times(1)).selectAll();
        verify(productRepository, times(1)).selectByCustomSearch(
                "Laptop",
                ProductCategory.UNDEFINED,
                BigDecimal.valueOf(1000000),
                BigDecimal.valueOf(3000000),
                "Lenovo",
                null,
                null,
                null,
                false
        );
    }

    @Test
    void disableProductByIdShouldDisableActiveProduct() throws BadRequestException {
        when(productRepository.selectById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.saveProduct(any())).thenReturn(mockProduct);
        boolean result = productService.disableProductById(1L);
        assertFalse(mockProduct.isActive());
        assertTrue(result);
        verify(productRepository).saveProduct(mockProduct);
    }

    @Test
    void disableProductByIdShouldThrowExceptionIfAlreadyDisabled() {
        mockProduct.setActive(false);
        when(productRepository.selectById(1L)).thenReturn(Optional.of(mockProduct));
        assertThrows(BadRequestException.class, () -> productService.disableProductById(1L));
        verify(productRepository, never()).saveProduct(any());
    }

    @Test
    void activateProductByIdShouldActivateInactiveProduct() throws BadRequestException {
        mockProduct.setActive(false);
        when(productRepository.selectById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.saveProduct(any())).thenReturn(mockProduct);
        boolean result = productService.activateProductById(1L);
        assertTrue(mockProduct.isActive());
        assertTrue(result);
        verify(productRepository).saveProduct(mockProduct);
    }

    @Test
    void activateProductByIdShouldThrowExceptionIfAlreadyActive() {
        when(productRepository.selectById(1L)).thenReturn(Optional.of(mockProduct));
        assertThrows(BadRequestException.class, () -> productService.activateProductById(1L));
        verify(productRepository, never()).saveProduct(any());
    }

    @Test
    void verifyValidStockShouldPassWhenStockIsValid() {
        assertDoesNotThrow(() -> productService.verifyValidStock(mockProduct));
        mockProduct.setStock(25);
        assertDoesNotThrow(() -> productService.verifyValidStock(mockProduct));
    }

    @Test
    void verifyValidStockShouldThrowIfLowerThanMin() {
        mockProduct.setStock(3);
        assertThrows(IllegalArgumentException.class, () -> productService.verifyValidStock(mockProduct));
    }

    @Test
    void verifyValidStockShouldThrowIfGreaterThanMax() {
        mockProduct.setStock(30);
        assertThrows(IllegalArgumentException.class, () -> productService.verifyValidStock(mockProduct));
    }

}