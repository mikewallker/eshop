package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void deleteById(String productId) {
        boolean removed = productData.removeIf(product -> product.getProductId().equals(productId));

        if (!removed) {
            throw new NoSuchElementException("Product with ID " + productId + " not found");
        }
    }


    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null if the product is not found
    }

    public Product update(String productId, Product updatedProduct) {
        Product productToUpdate = findById(productId);
        if (productToUpdate != null) {
            productToUpdate.setProductName(updatedProduct.getProductName());
            productToUpdate.setProductQuantity(updatedProduct.getProductQuantity());
            return productToUpdate;
        } else {
            throw new NoSuchElementException("Product with ID " + productId + " not found");
        }

    }
}
