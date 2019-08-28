package com.qualificationchecker.Qualification.Checker.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class QualifyingTotal {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Please enter a qualifying total")
    private int qualifyingTotal;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Weightlifter weightlifter;

    public QualifyingTotal(){}

    public QualifyingTotal(Event event, Weightlifter weightlifter, int qualifyingTotal){
        this.event = event;
        this.weightlifter = weightlifter;
        this.qualifyingTotal = qualifyingTotal;
    }

    public int getId() { return id; }

    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event;}

    public int getQualifyingTotal() { return qualifyingTotal; }

    public void setQualifyingTotal(int qualifyingTotal) { this.qualifyingTotal = qualifyingTotal; }

    public String toString() { return qualifyingTotal + "kg"; }

}
