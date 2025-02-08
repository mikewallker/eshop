package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
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
        return "productList";
    }

    // Display the edit form for a specific product
    @GetMapping("/edit/{productId}")
    public String editProductForm(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            return "editProduct"; // Return the edit-product.html template
        } else {
            return "error"; // Return an error page if the product is not found
        }
    }

    // Handle the form submission to update the product
    @PostMapping("/update")
    public String updateProduct(@RequestParam String productId,
                                @RequestParam String productName,
                                @RequestParam int productQuantity) {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName(productName);
        updatedProduct.setProductQuantity(productQuantity);

        service.update(productId, updatedProduct);
        return "redirect:/product/list"; // Redirect to the product list page
    }


}