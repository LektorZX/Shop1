package root.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import root.entity.Product;
import root.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class ShopController {

    private final ProductService productService;

    @Autowired
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String employee(Model model) {
        System.out.println();
        List<Product> all = productService.findAll();
        model.addAttribute("product", all);
        model.addAttribute("productDto", new Product());
        model.addAttribute("productDell", new Product());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        return "shop";
    }

    @PostMapping("/shop")
    public String employeeSave(Product product, @RequestParam String action) {

        System.out.println(action);
        switch (action) {
            case "Save":
                productService.save(product);
                break;
            case "Del":
                productService.deleteById(product.getId());
                break;
        }
        return "redirect:/shop";
    }
}
