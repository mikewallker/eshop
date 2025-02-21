package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {


    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        //may added later
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("d40f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateProduct() {
        // Arrange: Create and store a sample product
        Product sampleProduct = new Product();
        sampleProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        sampleProduct.setProductName("Sampo Cap Bambang");
        sampleProduct.setProductQuantity(100);
        productRepository.create(sampleProduct); // Store in the repository

        // positive scenario, create updated product details
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Anton");
        updatedProduct.setProductQuantity(50);

        Product result = productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Anton", result.getProductName());
        assertEquals(50, result.getProductQuantity());

        // nonexistent id
        Product nonExistentUpdate = new Product();

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productRepository.update("non-existent-id", nonExistentUpdate);
        });

        assertEquals("Product with ID non-existent-id not found", exception.getMessage());

        // Quantity == 0
        Product invalidQuantityUpdate = new Product();
        invalidQuantityUpdate.setProductName("Sampo Cap Error");
        invalidQuantityUpdate.setProductQuantity(0);

        Exception quantityException = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", invalidQuantityUpdate);
        });
        assertEquals("Product quantity must be greater than 0", quantityException.getMessage());

        // Test with negative quantity
        invalidQuantityUpdate.setProductQuantity(-10);
        Exception negativeQuantityException = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", invalidQuantityUpdate);
        });
        assertEquals("Product quantity must be greater than 0", negativeQuantityException.getMessage());
    }

    @Test
    void testDeleteById() {
        // Arrange: Create and store a sample product
        Product sampleProduct = new Product();
        sampleProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        sampleProduct.setProductName("Sampo Cap Bambang");
        sampleProduct.setProductQuantity(100);
        productRepository.create(sampleProduct); // Store in the repository

        //positive scenario
        productRepository.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));

        //negative scenario
        assertThrows(NoSuchElementException.class, () -> {
            productRepository.deleteById("non-existent-id");
        });
    }

}
