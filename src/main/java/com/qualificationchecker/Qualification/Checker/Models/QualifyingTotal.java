package com.qualificationchecker.Qualification.Checker.Models;

import javax.persistence.*;

@Entity
public class QualifyingTotal {

    @Id
    @GeneratedValue
    private int id;

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

    public int getQualifyingTotal() { return qualifyingTotal; }

    public void setQualifyingTotal(int qualifyingTotal) { this.qualifyingTotal = qualifyingTotal; }

    public String toString() { return qualifyingTotal + "kg"; }

}
