package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.UserDAO;
import com.qualificationchecker.Qualification.Checker.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register User Account");
        return "User/NewUser";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(Model model, @ModelAttribute @Valid User user,
                         Errors errors) {
        model.addAttribute(user);

        if (!errors.hasErrors()) {
            userDAO.save(user);
            return "Home";
        }
        return "User/NewUser";
    }

    @RequestMapping(value = "id={username}", method = RequestMethod.GET)
    public String userPage(Model model, @PathVariable String username) {

        return "User/Username";
    }

}