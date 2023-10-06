package com.example.geektrust.bo;

import com.example.geektrust.constants.PlanType;
import com.example.geektrust.constants.SubscriptionType;

public class Plan {
    private PlanType planType;
    private int months;
    private int cost;

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
