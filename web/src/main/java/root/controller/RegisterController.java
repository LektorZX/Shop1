package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import root.DTO.UserDto;
import root.entity.Product;
import root.service.user.UserService;

import java.util.Collection;
import java.util.List;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String user(Model model) {
        model.addAttribute("userDto", UserDto.builder().build());
        return "register";
    }

    @PostMapping("/register")
    public String userSave(UserDto userDto, @RequestParam String action) {

        System.out.println(action);
        switch (action) {
            case "Save":
                userService.save(userDto);
                return "redirect:/home";
            case "Exit":
                return "redirect:/home";
        }
        return "redirect:/register";
    }




}
