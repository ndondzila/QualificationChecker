package com.qualificationchecker.Qualification.Checker.Controllers;


import com.qualificationchecker.Qualification.Checker.Models.Data.EventDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.QualifyingTotalDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.WeightclassDAO;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("weightclasses", weightclassDAO.findAll());
        return "DataView/Index";
    }

    @RequestMapping(value = "totals", method = RequestMethod.GET)
    public String displayTotals(Model model) {

        model.addAttribute("weightclasses", weightclassDAO.findAll());
        model.addAttribute("events", eventDAO.findAll());
        model.addAttribute("title", "View all qualifying totals");

        return "DataView/Totals";
    }

    @RequestMapping(value = "weightclass/{weightclassId}", method = RequestMethod.GET)
    public String displayWeightclass(Model model, @PathVariable int weightclassId) {

        Weightclass weightclass = weightclassDAO.findOne(weightclassId);

        model.addAttribute("events", eventDAO.findAll());
        model.addAttribute("weightclass", weightclass);

        return "DataView/Weightclass";
    }

}
