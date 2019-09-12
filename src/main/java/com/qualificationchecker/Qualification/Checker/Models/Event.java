package com.qualificationchecker.Qualification.Checker.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Event name cannot be empty")
    private String name;

    @NotNull(message = "Please enter event year")
    @Min(value=2018, message = "Must be 2019 or later")
    private int year;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<QualifyingTotal> qualifyingTotals = new ArrayList<>();

    public Event(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Event(){ }

    public List<QualifyingTotal> getQualifyingTotals() { return qualifyingTotals; }
    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public void addQualifyingTotal(QualifyingTotal qualifyingTotal) { qualifyingTotals.add(qualifyingTotal); }

    public String toString() { return year + " " + name; }

    public Boolean hasQTs(){

        if(this.qualifyingTotals.size()<20){
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        } }

    public int getQualifyingTotalByWeightclass(Weightclass weightclass) {
        int eventQualifyingTotal = 0;
        for(int i = 0; i < qualifyingTotals.size(); i++) {
            QualifyingTotal qualifyingTotal = qualifyingTotals.get(i);
            if(qualifyingTotal.getWeightclass().equals(weightclass)) {
                eventQualifyingTotal = qualifyingTotal.getQualifyingTotal();
            }
        }
        return eventQualifyingTotal;
    }
}
