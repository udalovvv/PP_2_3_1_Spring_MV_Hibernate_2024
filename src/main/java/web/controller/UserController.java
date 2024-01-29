package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
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

    @GetMapping("/add")
    public String add(Model userModel) {
        userModel.addAttribute("newUser", new User());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newUser") @Valid User newUser,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "add";}
        userService.save(newUser);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.showUser(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       @ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "edit";}
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect: /";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.showUser(id));
        return "show";
    }



    @PostMapping("/editmodal")
    public String editWithModal(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user);
        return "redirect:/user";
    }



}
