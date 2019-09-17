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

        model.addAttribute("title", "Qualification Checker");
        model.addAttribute("womens", womens);
        model.addAttribute("mens", mens);
        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute(new CheckUserTotalForm());

        return "Home/Home";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processDisplayhome(Model model, @ModelAttribute @Valid CheckUserTotalForm checkUserTotalForm, Errors errors) {

        int userTotal = checkUserTotalForm.getUserTotal();
        Weightclass userWeightclass = weightclassDAO.findOne(checkUserTotalForm.getWeightclassId());

        Boolean overWR = Boolean.FALSE;
        Boolean overAR = Boolean.FALSE;

        if((userTotal>userWeightclass.getWorldRecord())){
            overWR = Boolean.TRUE;
        } else if ((userTotal>userWeightclass.getAmericanRecord())) {
            overAR = Boolean.TRUE;
        }

        if(errors.hasErrors()||overAR||overWR) {
            if(overWR) {
                model.addAttribute("overMessage", "Perhaps you entered your total in pounds instead of kilograms? You entered a total over the World Record in your weightclass and I can safely assume a World Record holder would not be concerned with their USAW event qualification status.  Enter your actual total in kilograms please!");
            } else if (overAR) {
                model.addAttribute("overMessage","Perhaps you entered your total in pounds instead of kilograms? You entered a total over the American Record in your weightclass.  Enter your actual total in kilograms please!");
            }
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
            model.addAttribute("title", "Qualification checker");

            return "Home/Home";}

        List<Event> events = new ArrayList<>();
        for(Event event: eventDAO.findAll()) {
            if (userWeightclass.hasQualifyingTotal(event)) {
                events.add(event);
            }
        }
        List<Event> qualified_events = new ArrayList<>(userWeightclass.getQualifiedEvents(userTotal));

        int ratioAR = Math.round((userTotal*100)/userWeightclass.getAmericanRecord());
        int ratioWR = Math.round((userTotal*100)/userWeightclass.getWorldRecord());

        model.addAttribute("weightclass", userWeightclass);
        model.addAttribute("userTotal", userTotal);
        model.addAttribute("ratioAR", ratioAR);
        model.addAttribute("ratioWR", ratioWR);
        model.addAttribute("events", events);
        model.addAttribute("title", "Results");

        if(qualified_events.size()<1) {
            model.addAttribute("results", "You do not yet qualify, but here is some information!"); }

        else {
            model.addAttribute("results", "With a total of " + userTotal + "kg at " + userWeightclass.getBodyweight() + ", you qualify for the following events:");}

        return "Home/Results";
    }
}
