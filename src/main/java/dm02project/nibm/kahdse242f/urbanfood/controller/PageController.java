package dm02project.nibm.kahdse242f.urbanfood.controller;


import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import dm02project.nibm.kahdse242f.urbanfood.entity.User;
import dm02project.nibm.kahdse242f.urbanfood.repository.ProductRepository;
import dm02project.nibm.kahdse242f.urbanfood.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        return "checkout";
    }

    @GetMapping("/product-details")
    public String productDetails() {
        return "product-details";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/add-product")
    public String addProduct() {
        return "add-product";
    }

    @GetMapping("/admin-login")
    public String adminLogin() {
        return "admin-login";
    }

}
