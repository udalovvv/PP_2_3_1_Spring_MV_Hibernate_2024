package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.showAll());
        model.addAttribute("newUser", new User());
        return "index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user")User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/user";
    }

}
