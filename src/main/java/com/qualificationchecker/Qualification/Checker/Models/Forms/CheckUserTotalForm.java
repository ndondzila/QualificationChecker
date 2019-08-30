package com.qualificationchecker.Qualification.Checker.Models.Forms;

import com.qualificationchecker.Qualification.Checker.Models.Weightclass;

import javax.validation.constraints.NotNull;

public class CheckUserTotalForm {

    private Iterable<Weightclass> weightlifters;

    @NotNull
    private int weightlifterId;

    @NotNull
    private int userTotal;

    public CheckUserTotalForm (){}

    public CheckUserTotalForm (Iterable<Weightclass> weightlifters, int userTotal) {
        this.weightlifters = weightlifters;
        this.userTotal = userTotal;
    }

    public int getWeightlifterId() { return weightlifterId; }
    public void setWeightlifterId(int weightlifterId) { this.weightlifterId = weightlifterId; }
    public Iterable<Weightclass> getWeightlifters() { return weightlifters; }
    public int getUserTotal() { return userTotal; }
    public void setUserTotal(int userTotal) { this.userTotal = userTotal; }

}
