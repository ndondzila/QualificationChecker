package com.qualificationchecker.Qualification.Checker.Models.Forms;

import com.qualificationchecker.Qualification.Checker.Models.Event;
import com.qualificationchecker.Qualification.Checker.Models.Weightlifter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddQualifyingTotalsForm {

    private Event event;
    private Iterable<Weightlifter> weightlifters;

    @NotNull
    private int eventId;

    @NotNull
    private int weightlifterId;

    @NotNull(message = "Please enter a qualifying total")
    @Min(value=1, message = "Please enter a qualifying total")
    private int qualifyingTotal;

    public AddQualifyingTotalsForm () {}

    public AddQualifyingTotalsForm (Event event, Iterable<Weightlifter> weightlifters, int qualifyingTotal) {
        this.event = event;
        this.weightlifters = weightlifters;
        this.qualifyingTotal = qualifyingTotal;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public int getWeightlifterId() { return weightlifterId; }
    public void setWeightlifterId(int weightlifterId) { this.weightlifterId = weightlifterId; }
    public Iterable<Weightlifter> getWeightlifters() { return weightlifters; }
    public Event getEvent() { return event; }
    public void setQualifyingTotal(int qualifyingTotal) { this.qualifyingTotal = qualifyingTotal; }
    public int getQualifyingTotal() { return qualifyingTotal; }
}
