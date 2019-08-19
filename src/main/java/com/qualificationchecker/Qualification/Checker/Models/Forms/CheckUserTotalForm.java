package com.qualificationchecker.Qualification.Checker.Models.Forms;

import com.qualificationchecker.Qualification.Checker.Models.Weightlifter;

import javax.validation.constraints.NotNull;

public class CheckUserTotalForm {

    private Iterable<Weightlifter> weightlifters;

    @NotNull
    private int weightlifterId;

    @NotNull
    private int userTotal;

    public CheckUserTotalForm (){}

    public CheckUserTotalForm (Iterable<Weightlifter> weightlifters, int userTotal) {
        this.weightlifters = weightlifters;
        this.userTotal = userTotal;
    }

    public int getWeightlifterId() { return weightlifterId; }
    public void setWeightlifterId(int weightlifterId) { this.weightlifterId = weightlifterId; }
    public Iterable<Weightlifter> getWeightlifters() { return weightlifters; }
    public int getUserTotal() { return userTotal; }
    public void setUserTotal(int userTotal) { this.userTotal = userTotal; }

}
