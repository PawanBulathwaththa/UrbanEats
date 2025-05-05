package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import dm02project.nibm.kahdse242f.urbanfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("productsByCategory", productService.getAllProductsByCategories());
        return "index";
    }

    @GetMapping("/products/category/{category}")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/shop-grid")
    public String showShopPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop-grid"; // This maps to shop-grid.html in templates folder
    }

    @GetMapping("/product-details/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        // parse the gallery images and send to the view
        model.addAttribute("galleryImages", productService.parseGalleryImages(product.getImageGallery()));

        return "product-details";  // your Thymeleaf view
    }
    @GetMapping("/product-details-ajax/{id}")
    public String getProductDetailsAjax(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "fragments/product-detail :: productContent";
    }



}