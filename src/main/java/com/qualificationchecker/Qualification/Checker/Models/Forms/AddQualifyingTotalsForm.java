package com.qualificationchecker.Qualification.Checker.Models.Forms;

import com.qualificationchecker.Qualification.Checker.Models.Data.EventDAO;
import com.qualificationchecker.Qualification.Checker.Models.Data.WeightlifterDAO;
import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Weightlifter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

public class AddQualifyingTotalsForm {

    private Event event;
    private Iterable<Weightlifter> weightlifters;

    @NotNull
    private int eventId;

    @NotNull
    private int weightlifterId;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private WeightlifterDAO weightlifterDAO;

    public AddQualifyingTotalsForm () {}

    public AddQualifyingTotalsForm (Event event, Iterable<Weightlifter> weightlifters) {
        this.event = event;
        this.weightlifters = weightlifters;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public int getWeightlifterId() { return weightlifterId; }
    public void setWeightlifterId(int weightlifterId) { this.weightlifterId = weightlifterId; }
    public Iterable<Weightlifter> getWeightlifters() { return weightlifters; }
    public Event getEvent() { return event; }
}
