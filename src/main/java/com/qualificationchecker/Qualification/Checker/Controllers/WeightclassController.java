package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.WeightclassDAO;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("data")
public class WeightclassController {

    @Autowired
    private WeightclassDAO weightclassDAO;

    @RequestMapping(value = "totals")
    public String index(Model model) {

        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute("title", "Qualifying Totals");

        return "DataPages/QualifyingTotals";
    }

    @RequestMapping(value = "entry", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute(new Weightclass());
        model.addAttribute("title", "Enter weightclass data");
        return "DataPages/Weightclasses";
    }

    @RequestMapping(value = "entry", method = RequestMethod.POST)
    public String signup(Model model, @ModelAttribute Weightclass weightclass) {

        model.addAttribute(weightclass);
        weightclassDAO.save(weightclass);

        return "redirect:/data/totals";
    }
}
