package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.WeightclassDAO;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
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
    private WeightclassDAO weightclassDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayhome(Model model) {

        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute("title", "Qualification Checker");

        return "Home/Home";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processhome(Model model, @RequestParam int weightclass, @RequestParam int userTotal) {

        Weightclass comparison = weightclassDAO.findOne(weightclass);
        String level = new String();
        String bw = comparison.getBodyweight();

        int AOseriestotal = comparison.getAOseries();
        int AOfinalstotal = comparison.getAOfinals();
        int nationalstotal = comparison.getNationals();

        if (userTotal>nationalstotal) {level = "n";}
        else if (userTotal>AOfinalstotal) {level = "a";}
        else if (userTotal>AOseriestotal) {level = "s";}
        else {level="0";}

        return "redirect:/results/" + level + "/" + bw + "/" + userTotal;
    }

    @RequestMapping(value = "results/{level}/{bw}/{total}", method = RequestMethod.GET)
    public String results(Model model, @PathVariable String level, @PathVariable String bw, @PathVariable int total) {
        model.addAttribute("level", level);
        model.addAttribute("bw", bw);
        model.addAttribute("total", total);
        model.addAttribute("title", "Results Page");
        return "Home/Results";
    }

}
