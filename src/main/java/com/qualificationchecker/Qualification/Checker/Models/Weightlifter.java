package com.qualificationchecker.Qualification.Checker.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Weightlifter {

    @Id
    @GeneratedValue
    private int id;

    private String gender;
    private String bodyweight;
    private int record;

    @OneToMany
    @JoinColumn(name = "weightlifter_id")
    private List<QualifyingTotal> qualifyingTotals = new ArrayList<>();

    public Weightlifter(String gender, String bodyweight, int record) {
        this.gender = gender;
        this.bodyweight = bodyweight;
        this.record = record;
    }

    public Weightlifter(){}

    public int getId() { return id; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getBodyweight() { return bodyweight; }

    public void setBodyweight(String bodyweight) { this.bodyweight = bodyweight; }

    public int getRecord() { return record; }

    public void setRecord(int record) { this.record = record; }

    public void addQualifyingTotal(QualifyingTotal total) {qualifyingTotals.add(total);}

    public List<QualifyingTotal> getQualifyingTotals() { return qualifyingTotals; }

    public String toString() {return bodyweight + " "+ gender;}
}