package com.qualificationchecker.Qualification.Checker.Controllers;

import com.qualificationchecker.Qualification.Checker.Models.Data.EventDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.QualifyingTotalDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.WeightclassDAO;
import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Forms.AddQualifyingTotalsForm;
import com.qualificationchecker.Qualification.Checker.Models.QualifyingTotal;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("data")
public class DataEntryController {

    @Autowired
    private WeightclassDAO weightclassDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private QualifyingTotalDAO qualifyingTotalDAO;

    @RequestMapping(value = "weightclass", method = RequestMethod.GET)
    public String weightlifter(Model model) {

        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute(new Weightclass());

        return "DataPages/Weightclass"; }


    @RequestMapping(value = "weightclass", method = RequestMethod.POST)
    public String processWeightlifter(Model model, @ModelAttribute @Valid Weightclass weightclass) {

        if(weightclassDAO.count()==20) {
            model.addAttribute("complete", "All weightclasses have been entered!");
            model.addAttribute("weightclass", weightclassDAO.findAll());
            return "DataPages/Weightclass"; }

        model.addAttribute(weightclass);
        weightclassDAO.save(weightclass);

        return "redirect:/data/weightclass"; }


    @RequestMapping(value = "event", method = RequestMethod.GET)
    public String displayEvent(Model model) {

        model.addAttribute("events", eventDAO.findAll());
        model.addAttribute(new Event());

        return "DataPages/Event"; }


    @RequestMapping(value = "event", method = RequestMethod.POST)
    public String processEvent(Model model, @ModelAttribute @Valid Event event, Errors errors) {

        Boolean duplicateEvent = Boolean.FALSE;

        Iterator<Event> eventIterator = eventDAO.findAll().iterator();
        while(eventIterator.hasNext()) {
            if(eventIterator.next().toString().equals(event.toString())) {
                duplicateEvent = Boolean.TRUE; } }

        if(errors.hasErrors()||duplicateEvent) {

            if(duplicateEvent) {
                model.addAttribute("duplicate", "Event already entered!"); }

            model.addAttribute("events", eventDAO.findAll());

            return "DataPages/Event"; }

        model.addAttribute(event);
        eventDAO.save(event);

        return "redirect:/data/event"; }


    @RequestMapping(value = "qualifyingtotals/{eventId}", method = RequestMethod.GET)
    public String qualifyingTotals(Model model, @PathVariable int eventId) {

        Event event = eventDAO.findOne(eventId);

        List<Weightclass> hasQT = new ArrayList<>();
        List<Weightclass> noQT = new ArrayList<>();
        Iterable<Weightclass> weightclassIterable = weightclassDAO.findAll();

        for(Weightclass weightclass: weightclassIterable) {
            if(weightclass.hasQualifyingTotal(event)) {
                hasQT.add(weightclass);} else {
                noQT.add(weightclass); } }

        AddQualifyingTotalsForm form = new AddQualifyingTotalsForm(event,0);
        model.addAttribute("event", event);
        model.addAttribute("title", "Update " + event.toString() + " qualifying totals:");
        model.addAttribute("hasQT", hasQT);
        model.addAttribute("noQT", noQT);
        model.addAttribute("form", form);

        return "DataPages/QualifyingTotals"; }


    @RequestMapping(value = "qualifyingtotals/{eventId}", method = RequestMethod.POST)
    public String processQualifyingTotals(Model model, @ModelAttribute @Valid AddQualifyingTotalsForm form, Errors errors, @RequestParam int weightclassId) {

        Event event = eventDAO.findOne(form.getEventId());

        if(errors.hasErrors()) {

            //TODO: Figure out how to validate and return error message for null, 0, or >WR/AR total??

            return "DataPages/QualifyingTotals";
        }

        Weightclass weightclass = weightclassDAO.findOne(weightclassId);
        QualifyingTotal qualifyingTotal = new QualifyingTotal(event, weightclass, form.getQualifyingTotal());
        qualifyingTotalDAO.save(qualifyingTotal);

        return "redirect:/data/qualifyingtotals/" + form.getEventId(); }

}
