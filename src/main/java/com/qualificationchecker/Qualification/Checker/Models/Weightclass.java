package com.qualificationchecker.Qualification.Checker.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Weightclass {

    @Id
    @GeneratedValue
    private int id;

    private String gender;
    private String bodyweight;
    private Integer AOseries;
    private Integer AOfinals;
    private Integer nationals;

    public Weightclass(String gender, String bodyweight, Integer AOseries, Integer AOfinals, Integer nationals) {
        this.gender = gender;
        this.bodyweight = bodyweight;
        this.AOseries = AOseries;
        this.AOfinals = AOfinals;
        this.nationals = nationals;
    }

    public Weightclass(){}

    public int getId() { return id; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getBodyweight() { return bodyweight; }

    public void setBodyweight(String bodyweight) { this.bodyweight = bodyweight; }

    public Integer getAOseries() { return AOseries; }

    public void setAOseries(Integer AOseries) { this.AOseries = AOseries; }

    public Integer getAOfinals() { return AOfinals; }

    public void setAOfinals(Integer AOfinals) { this.AOfinals = AOfinals; }

    public Integer getNationals() { return nationals; }

    public void setNationals(Integer nationals) { this.nationals = nationals; }
}