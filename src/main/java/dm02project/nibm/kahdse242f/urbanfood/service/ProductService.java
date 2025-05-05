package dm02project.nibm.kahdse242f.urbanfood.service;

import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import dm02project.nibm.kahdse242f.urbanfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final List<String> categories = Arrays.asList(
            "Food and Drinks",
            "Vegetables",
            "Dried Foods",
            "Bread and Cake",
            "Fish and Meat"
    );

    public List<String> getAllCategories() {
        return categories;
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    public Map<String, List<Product>> getAllProductsByCategories() {
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        for (String category : categories) {
            List<Product> products = getProductsByCategory(category);
            productsByCategory.put(category, products);
        }

        return productsByCategory;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<String> parseGalleryImages(String gallery) {
        if (gallery != null && !gallery.isEmpty()) {
            return Arrays.asList(gallery.split(","));
        }
        return new ArrayList<>();
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product); // Implement this in repo
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteProductById(productId);
    }

}