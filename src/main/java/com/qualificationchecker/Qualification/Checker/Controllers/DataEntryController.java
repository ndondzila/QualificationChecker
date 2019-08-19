package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.EventDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.QualifyingTotalDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.WeightlifterDAO;
import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Forms.AddQualifyingTotalsForm;
import com.qualificationchecker.Qualification.Checker.Models.QualifyingTotal;
import com.qualificationchecker.Qualification.Checker.Models.Weightlifter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("data")
public class DataEntryController {

    @Autowired
    private WeightlifterDAO weightlifterDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private QualifyingTotalDAO qualifyingTotalDAO;

    @RequestMapping(value = "weightlifter", method = RequestMethod.GET)
    public String weightlifter(Model model) {

        model.addAttribute("weightlifters", weightlifterDAO.findAll());
        model.addAttribute(new Weightlifter());

        return "DataPages/Weightlifter";
    }

    @RequestMapping(value = "weightlifter", method = RequestMethod.POST)
    public String processWeightlifter(Model model, @ModelAttribute Weightlifter weightlifter) {

        model.addAttribute(weightlifter);
        weightlifterDAO.save(weightlifter);

        return "redirect:/data/weightlifter";
    }

    @RequestMapping(value = "event", method = RequestMethod.GET)
    public String event(Model model) {

        model.addAttribute("events", eventDAO.findAll());
        model.addAttribute(new Event());

        return "DataPages/Event";
    }

    @RequestMapping(value = "event", method = RequestMethod.POST)
    public String processEvent(Model model, @ModelAttribute Event event) {

        model.addAttribute(event);
        eventDAO.save(event);

        return "redirect:/data/event";
    }

    @RequestMapping(value = "qualifyingtotals/{eventId}", method = RequestMethod.GET)
    public String qualifyingTotals(Model model, @PathVariable int eventId) {

        Event event = eventDAO.findOne(eventId);
        AddQualifyingTotalsForm form = new AddQualifyingTotalsForm(event, weightlifterDAO.findAll());
        model.addAttribute("title", "Update " + event.toString() + " qualifying totals:");
        model.addAttribute("weighlifters", weightlifterDAO.findAll());
        model.addAttribute("form", form);

        return "DataPages/QualifyingTotals";
    }

    @RequestMapping(value = "qualifyingtotals/{eventId}", method = RequestMethod.POST)
    public String processQualifyingTotals(Model model, @PathVariable int eventId, @ModelAttribute @Valid AddQualifyingTotalsForm form, @RequestParam int total) {

        Weightlifter weightlifter = weightlifterDAO.findOne(form.getWeightlifterId());
        Event event = eventDAO.findOne(eventId);
        QualifyingTotal qualifyingTotal = new QualifyingTotal(event, weightlifter, total);
        qualifyingTotalDAO.save(qualifyingTotal);

        return "redirect:/data/qualifyingtotals/" + eventId;
    }
}
