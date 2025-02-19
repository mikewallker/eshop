package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(1500);

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("1", createdProduct.getProductId());
        assertEquals("Laptop", createdProduct.getProductName());
        assertEquals(1500, createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(1500);

        when(productRepository.findById("1")).thenReturn(product);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("1", foundProduct.getProductId());
        assertEquals("Laptop", foundProduct.getProductName());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Gaming Laptop");
        updatedProduct.setProductQuantity(2000);

        when(productRepository.update(eq("1"), any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.update("1", updatedProduct);

        assertNotNull(result);
        assertEquals("1", result.getProductId());
        assertEquals("Gaming Laptop", result.getProductName());
        assertEquals(2000, result.getProductQuantity());
        verify(productRepository, times(1)).update("1", updatedProduct);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Laptop");
        product1.setProductQuantity(1500);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Phone");
        product2.setProductQuantity(800);

        Iterator<Product> productIterator = Arrays.asList(product1, product2).iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getProductName());
        assertEquals("Phone", products.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        doNothing().when(productRepository).deleteById("1");

        productService.deleteById("1");

        verify(productRepository, times(1)).deleteById("1");
    }
}
