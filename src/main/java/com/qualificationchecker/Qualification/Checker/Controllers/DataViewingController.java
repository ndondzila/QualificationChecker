package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.EventDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.QualifyingTotalDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.WeightclassDAO;
import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class DataViewingController {

    @Autowired
    private WeightclassDAO weightclassDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private QualifyingTotalDAO qualifyingTotalDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        List<Weightclass> womens = new ArrayList<>();
        List<Weightclass> mens = new ArrayList<>();
        for(Weightclass weightclass: weightclassDAO.findAll()) {
            if(weightclass.getGender().equals("F")) {
                womens.add(weightclass);
            } else {mens.add(weightclass); }
        }
        model.addAttribute("title", "Individual weightclass data");
        model.addAttribute("womens", womens);
        model.addAttribute("mens", mens);
        return "DataView/Index";
    }

    @RequestMapping(value = "totals", method = RequestMethod.GET)
    public String displayTotals(Model model) {

        List<Event> events = new ArrayList<>();
        for(Event event: eventDAO.findAll()) {
            if (event.hasQTs()) {
                events.add(event);
            } }
        model.addAttribute("title", "All current qualifying totals");
        model.addAttribute("events", events);
        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute("title", "View all qualifying totals");

        return "DataView/Totals";
    }

    @RequestMapping(value = "weightclass/{weightclassId}", method = RequestMethod.GET)
    public String displayWeightclass(Model model, @PathVariable int weightclassId) {

        Weightclass weightclass = weightclassDAO.findOne(weightclassId);

        List<Event> events = new ArrayList<>();

        for(Event event: eventDAO.findAll()) {
            if (weightclass.hasQualifyingTotal(event)) {
                events.add(event);
            }
        }
        model.addAttribute("title", weightclass.toString() +" weightclass data");
        model.addAttribute("events", events);
        model.addAttribute("weightclass", weightclass);

        return "DataView/Weightclass";
    }

}
