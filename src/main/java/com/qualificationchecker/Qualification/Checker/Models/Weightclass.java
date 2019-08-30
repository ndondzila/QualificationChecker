package com.qualificationchecker.Qualification.Checker.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Weightclass {

    @Id
    @GeneratedValue
    private int id;

    private String gender;
    private String bodyweight;

    @NotNull
    private int record;

    @OneToMany
    @JoinColumn(name = "weightclass_id")
    private List<QualifyingTotal> qualifyingTotals = new ArrayList<>();

    public Weightclass(String gender, String bodyweight, int record) {
        this.gender = gender;
        this.bodyweight = bodyweight;
        this.record = record;
    }

    public Weightclass(){}

    public int getId() { return id; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getBodyweight() { return bodyweight; }

    public void setBodyweight(String bodyweight) { this.bodyweight = bodyweight; }

    public int getRecord() { return record; }

    public void setRecord(int record) { this.record = record; }

    public void addQualifyingTotal(QualifyingTotal total) {qualifyingTotals.add(total);}

    public List<QualifyingTotal> getQualifyingTotals() { return qualifyingTotals; }

    public List<Event> getQualifiedEvents(int total) {
        List<Event> qualified_events = new ArrayList<>();
        for(int i = 0; i < qualifyingTotals.size(); i++) {
            QualifyingTotal qualifyingTotal = qualifyingTotals.get(i);
            if(total>qualifyingTotal.getQualifyingTotal()) {
                Event event = qualifyingTotal.getEvent();
                qualified_events.add(event);
            }
        }
        return qualified_events;
    }

    public Boolean hasQualifyingTotal(Event event) {
        Boolean hasQT = Boolean.FALSE;
        for(int i = 0; i < qualifyingTotals.size(); i++) {
            QualifyingTotal qualifyingTotal = qualifyingTotals.get(i);
            if(qualifyingTotal.getEvent().equals(event)) {
                hasQT = Boolean.TRUE;
            }
        }
        return hasQT;
    }

    public String getEventQualifyingTotal(Event event) {
        String eventQualifyingTotal = new String();
        for(int i = 0; i < qualifyingTotals.size(); i++) {
            QualifyingTotal qualifyingTotal = qualifyingTotals.get(i);
            if(qualifyingTotal.getEvent().equals(event)) {
                eventQualifyingTotal = qualifyingTotal.toString();
            }
        }
        return eventQualifyingTotal;
    }
    public String toString(){return bodyweight + " "+ gender;}
}