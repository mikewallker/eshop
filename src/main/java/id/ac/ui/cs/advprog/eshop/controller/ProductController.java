package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        service.deleteById(productId);
        return "redirect:/product/list";
    }

    // Display the edit form for a specific product
    @GetMapping("/edit/{productId}")
    public String editProductForm(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            return "EditProduct"; // Return the edit-product.html template
        } else {
            return "error"; // Return an error page if the product is not found
        }
    }

    // Handle the form submission to update the product
    @PostMapping("/update")
    public String updateProduct(@RequestParam String productId,
                                @RequestParam String productName,
                                @RequestParam int productQuantity,
                                @ModelAttribute Product currentProduct,
                                Model model) {
        try {
            Product updatedProduct = new Product();
            updatedProduct.setProductId(productId);
            updatedProduct.setProductName(productName);
            updatedProduct.setProductQuantity(productQuantity);

            service.update(productId, updatedProduct);
            return "redirect:/product/list"; // Redirect to the product list page
        } catch(IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("product", currentProduct); // Preserve entered data
            return "EditProduct";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "EditProduct";
        }
    }



}