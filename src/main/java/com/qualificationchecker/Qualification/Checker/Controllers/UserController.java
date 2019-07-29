package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register User Account");
        return "User/NewUser";
    }

}