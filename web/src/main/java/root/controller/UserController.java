package root.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user")
    public String showAdminPage() {
      //  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return "user";
    }
}
