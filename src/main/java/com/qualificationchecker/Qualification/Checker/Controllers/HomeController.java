package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.EventDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.WeightclassDAO;
import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Forms.CheckUserTotalForm;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private WeightclassDAO weightclassDAO;

    @Autowired
    private EventDAO eventDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayhome(Model model) {

        List<Weightclass> womens = new ArrayList<>();
        List<Weightclass> mens = new ArrayList<>();
        for(Weightclass weightclass: weightclassDAO.findAll()) {
            if(weightclass.getGender().equals("F")) {
                womens.add(weightclass);
            } else {mens.add(weightclass); }
        }
        model.addAttribute("womens", womens);
        model.addAttribute("mens", mens);
        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute("title", "Qualification Checker");
        model.addAttribute(new CheckUserTotalForm());

        return "Home/Home";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processDisplayhome(Model model,  @RequestParam String userTotal, @ModelAttribute @Valid CheckUserTotalForm checkUserTotalForm, Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("weightlifters", weightclassDAO.findAll());
            return "Home/Home"; }

        int total = checkUserTotalForm.getUserTotal();
        Weightclass weightclass = weightclassDAO.findOne(checkUserTotalForm.getWeightlifterId());
        List<Event> qualified_events = new ArrayList<>(weightclass.getQualifiedEvents(total));

        if(qualified_events.size()<1) {
            model.addAttribute("results", "You do not qualify"); }

        else {
            model.addAttribute("results", "With a total of " + total + "kg at " + weightclass.getBodyweight() + ", you qualify for the following events:");
            model.addAttribute("events", qualified_events); }

        model.addAttribute("title", "Qualification Checker");

        return "Home/Results";
    }

}
