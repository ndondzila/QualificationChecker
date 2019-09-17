package com.qualificationchecker.Qualification.Checker.Models.Forms;

import com.qualificationchecker.Qualification.Checker.Models.Weightclass;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CheckUserTotalForm {

    @NotNull
    @Min(value=1, message = "Please select a weightclass!")
    private int weightclassId;

    @NotNull
    @Min(value=1, message = "Please enter a total!")
    private int userTotal;

    public CheckUserTotalForm (){}

    public CheckUserTotalForm (int weightclassId, int userTotal) {
        this.weightclassId = weightclassId;
        this.userTotal = userTotal;
    }

    public int getWeightclassId() { return weightclassId;}
    public void setWeightclassId(int weightclassId) { this.weightclassId = weightclassId;}
    public int getUserTotal() { return userTotal; }
    public void setUserTotal(int userTotal) { this.userTotal = userTotal; }

}
