package com.qualificationchecker.Qualification.Checker.Models.Forms;

import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Weightclass;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddQualifyingTotalsForm {

    private Event event;
    private Iterable<Weightclass> weightclasses;

    @NotNull
    private int eventId;

    @NotNull
    private int weightclassId;

    @NotNull(message = "Please enter a qualifying total")
    @Min(value=1, message = "Please enter a qualifying total")
    private int qualifyingTotal;

    public AddQualifyingTotalsForm () {}

    public AddQualifyingTotalsForm (Event event, Iterable<Weightclass> weightclasses, int qualifyingTotal) {
        this.event = event;
        this.weightclasses = weightclasses;
        this.qualifyingTotal = qualifyingTotal;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public int getWeightclassId() { return weightclassId; }
    public void setWeightlifterId(int weightclassId) { this.weightclassId = weightclassId; }
    public Iterable<Weightclass> getWeightclasses() { return weightclasses; }
    public Event getEvent() { return event; }
    public void setQualifyingTotal(int qualifyingTotal) { this.qualifyingTotal = qualifyingTotal; }
    public int getQualifyingTotal() { return qualifyingTotal; }
}
