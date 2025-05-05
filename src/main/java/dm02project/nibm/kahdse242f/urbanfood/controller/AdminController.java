package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import dm02project.nibm.kahdse242f.urbanfood.repository.AdminRepository;
import dm02project.nibm.kahdse242f.urbanfood.repository.OrderRepository;
import dm02project.nibm.kahdse242f.urbanfood.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin-login"; // Your HTML template
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        boolean isValid = adminRepository.validateAdminLogin(username, password);
        if (isValid) {
            session.setAttribute("adminUsername", username); // store in session
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "admin-login";
        }
    }

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session) {
        if (session.getAttribute("adminUsername") == null) {
            return "redirect:/admin/login";
        }
        return "admin-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "add-product";
    }



    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product"; // Thymeleaf HTML form page
    }


    @PostMapping("/add-product")
    public String addProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam(required = false) Double old_price,
            @RequestParam(required = false) Integer stock,
            @RequestParam String category,
            @RequestParam(required = false) String badge,
            @RequestParam("image_main") MultipartFile imageMain,
            @RequestParam(value = "image_gallery", required = false) MultipartFile[] imageGallery
    ) {
        try {
            String imageMainName = imageMain.getOriginalFilename();

            // Convert gallery to comma-separated string
            StringBuilder galleryBuilder = new StringBuilder();
            if (imageGallery != null) {
                for (MultipartFile file : imageGallery) {
                    if (!file.isEmpty()) {
                        galleryBuilder.append(file.getOriginalFilename()).append(",");
                    }
                }
            }
            String imageGalleryString = galleryBuilder.toString();

            jdbcTemplate.execute((Connection con) -> {
                CallableStatement cs = con.prepareCall("{ call INSERT_PRODUCT(?, ?, ?, ?, ?, ?, ?, ?, ?) }");
                cs.setString(1, name);
                cs.setString(2, description);
                cs.setDouble(3, price);
                if (old_price != null) cs.setDouble(4, old_price); else cs.setNull(4, Types.DOUBLE);
                if (stock != null) cs.setInt(5, stock); else cs.setNull(5, Types.INTEGER);
                cs.setString(6, imageMainName);
                cs.setString(7, imageGalleryString);
                cs.setString(8, category);
                cs.setString(9, badge);
                return cs;
            });

            System.out.println("✅ Product '" + name + "' successfully inserted into the database.");
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/admin/add-product"; // or wherever your product list is
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/add-product";  // or wherever your product list page is
    }




    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<Map<String, Object>>> getOrderItems(@PathVariable int orderId) {
        try {
            return ResponseEntity.ok(orderRepository.getOrderItemsByOrderId(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

