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
    public String index(Model userModel) {
        userModel.addAttribute("users", userService.showAll());
        userModel.addAttribute("newUser", new User());
        return "index";
    }

    @GetMapping("/new")
    public String addNew(Model userModel) {
        userModel.addAttribute("newUser", new User());
        return "new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user")User newUser) {
        userService.save(newUser);
        return "redirect: /user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect: /user";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.showUser(id));
        return "show";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.showUser(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") long id ,@ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/user";
    }

    @PostMapping("/updatemodal")
    public String updateWithModal(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user);
        return "redirect:/user";
    }



}
