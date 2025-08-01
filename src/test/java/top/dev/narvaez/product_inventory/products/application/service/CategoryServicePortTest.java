package top.dev.narvaez.product_inventory.products.application.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import top.dev.narvaez.product_inventory.ProductDummyMock;
import top.dev.narvaez.product_inventory.products.domain.models.CategoryModel;
import top.dev.narvaez.product_inventory.products.domain.models.ProductCategory;
import top.dev.narvaez.product_inventory.products.domain.ports.out.CategoryRepositoryPort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServicePortTest {

    @Mock
    CategoryRepositoryPort categoryRepository;

    @InjectMocks
    CategoryServicePort categoryService;

    ProductDummyMock mockMapper;

    @BeforeEach
    void setUp() {
        mockMapper = new ProductDummyMock();
    }

    @Test
    @DisplayName("saveCategory - Should return the created Category Model")
    void saveCategoryShouldReturnCreatedCategoryModel() {
        ProductCategory category = ProductCategory.UNDEFINED;
        CategoryModel newCategory = mockMapper.createCategoryModel(null, category, null);

        when(categoryRepository.saveCategory(any())).thenAnswer(invocationOnMock -> {
            CategoryModel toSave = invocationOnMock.getArgument(0);
            toSave.setId(15L);
            return toSave;
        });

        CategoryModel savedCategory = categoryService.saveCategory(newCategory);

        assertNotNull(savedCategory.getId());
        assertNotNull((savedCategory.getName()));
        assertNull((savedCategory.getDescription()));
        assertEquals(newCategory.getName(), savedCategory.getName());
        assertEquals(ProductCategory.UNDEFINED, savedCategory.getName());
        verify(categoryRepository).saveCategory(any(CategoryModel.class));
    }

    @Test
    @DisplayName("updateCategory - Should return the updated Category Model")
    void updateCategoryWithNullRestBodyIdShouldReturnUpdatedCategoryModel() {
        ProductCategory categoryName = ProductCategory.UNDEFINED;
        CategoryModel oldCategory = mockMapper.mapCategoryModel();
        CategoryModel newCategory = mockMapper.createCategoryModel(null, categoryName, "undefined");

        when(categoryRepository.selectById(12L)).thenReturn(Optional.of(oldCategory));
        when(categoryRepository.saveCategory(any())).thenAnswer(invocationOnMock -> {
            CategoryModel toUpdate = invocationOnMock.getArgument(0);
            toUpdate.setName(newCategory.getName());
            toUpdate.setDescription(newCategory.getDescription());
            return toUpdate;
        });

        CategoryModel updatedCategory = categoryService.updateCategory(newCategory, 12L);

        assertNotNull(updatedCategory.getId());
        assertNotNull((updatedCategory.getName()));
        assertNotNull((updatedCategory.getDescription()));
        assertEquals(oldCategory.getId(), updatedCategory.getId());
        assertEquals(newCategory.getName(), updatedCategory.getName());
        assertEquals(newCategory.getDescription(), updatedCategory.getDescription());
        assertEquals(ProductCategory.UNDEFINED, updatedCategory.getName());
        verify(categoryRepository).selectById(12L);
        verify(categoryRepository).saveCategory(any(CategoryModel.class));
    }

    @Test
    @DisplayName("updateCategory - Should throws IllegalArgumentException")
    void updateCategoryWithInvalidRestBodyIdShouldReturnUpdatedCategoryModel() {

        CategoryModel newCategory = mockMapper.createCategoryModel(10L, ProductCategory.UNDEFINED, "undefined");

        assertThrows(IllegalArgumentException.class, () ->
                categoryService.updateCategory(newCategory, 12L)
        );

    }

    @Test
    @DisplayName("findCategoryById - Should return a category when it exists")
    void findCategoryByIdShouldReturnCategory() {
        CategoryModel expectedCategory = mockMapper.mapCategoryModel();
        when(categoryRepository.selectById(anyLong())).thenReturn(Optional.of(expectedCategory));

        CategoryModel actualCategory = categoryService.findCategoryById(12L);

        assertEquals(expectedCategory, actualCategory);
        verify(categoryRepository).selectById(anyLong());
    }

    @Test
    @DisplayName("findCategoryById - Should throw EntityNotFoundException when category does not exist")
    void findCategoryByIdShouldThrowExceptionWhenNotFound() {
        when(categoryRepository.selectById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                categoryService.findCategoryById(16L)
        );

        verify(categoryRepository).selectById(anyLong());
    }

    @Test
    @DisplayName("findCategoryByName - Should return a category when it exists")
    void findCategoryByNameShouldReturnCategory() {
        CategoryModel expectedCategory = mockMapper.mapCategoryModel();
        when(categoryRepository.selectByName(any())).thenReturn(Optional.of(expectedCategory));

        CategoryModel actualCategory = categoryService.findCategoryByName(ProductCategory.CANNED.name());

        assertEquals(expectedCategory, actualCategory);
        verify(categoryRepository).selectByName(any());
    }

    @Test
    @DisplayName("findCategoryByName - Should throw EntityNotFoundException when category does not exist")
    void findCategoryByNameShouldThrowExceptionWhenNotFound() {
        when(categoryRepository.selectByName(ProductCategory.CANNED)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                categoryService.findCategoryByName(ProductCategory.CANNED.name())
        );

        verify(categoryRepository).selectByName(ProductCategory.CANNED);
    }

    @Test
    @DisplayName("findAllCategories - Should return a list of categories")
    void findAllCategoriesShouldReturnCategoryList() {
        List<CategoryModel> expectedCategories = mockMapper.mapAnyCategoryModelList();
        when(categoryRepository.selectAll()).thenReturn(expectedCategories);
        List<CategoryModel> actualCategories = categoryService.findAllCategories();

        assertEquals(expectedCategories.size(), actualCategories.size());
        assertEquals(expectedCategories, actualCategories);
        verify(categoryRepository).selectAll();
    }

}