package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.ProductReview;
import dm02project.nibm.kahdse242f.urbanfood.service.ProductReviewService;
import dm02project.nibm.kahdse242f.urbanfood.service.ProductService;
import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductService productService;

    // Show product details and reviews
    @GetMapping("/details/{id}")
    public String showProductDetails(@PathVariable("id") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        List<ProductReview> reviews = productReviewService.getReviewsForProduct(productId);

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("newReview", new ProductReview());
        model.addAttribute("galleryImages", productService.parseGalleryImages(product.getImageGallery()));

        return "product-details";
    }

    // Save a new review
    @PostMapping("/submitReview/{id}")
    public String submitReview(@PathVariable("id") Long productId,
                               @ModelAttribute("newReview") ProductReview review) {
        review.setProductId(productId);
        productReviewService.saveReview(review);
        return "redirect:/product/details/" + productId;
    }
}