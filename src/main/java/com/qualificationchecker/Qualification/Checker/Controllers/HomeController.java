package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.WeightlifterDAO;
import com.qualificationchecker.Qualification.Checker.Models.Weightlifter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    private WeightlifterDAO weightlifterDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayhome(Model model) {

        model.addAttribute("weightclasses", weightlifterDAO.findAll());
        model.addAttribute("title", "Qualification Checker");

        return "Home/Home";
    }
    

}
