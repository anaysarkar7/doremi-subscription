package com.example.geektrust.bo.subscriptions;

import com.example.geektrust.bo.Plan;
import com.example.geektrust.constants.PlanType;
import com.example.geektrust.constants.SubscriptionType;

public class PodcastSubscription extends Subscription{
    public PodcastSubscription() {
        super();
        plan = new Plan();
        subscriptionType = SubscriptionType.PODCAST;
    }

    @Override
    public void setPlan(String planType) {
        if(planType.equals(PlanType.FREE.name())) {
            plan.setPlanType(PlanType.FREE);
            plan.setMonths(1);
            plan.setCost(0);
        } else if(planType.equals(PlanType.PERSONAL.name())) {
            plan.setPlanType(PlanType.PERSONAL);
            plan.setMonths(1);
            plan.setCost(100);
        } else if(planType.equals(PlanType.PREMIUM.name())){
            plan.setPlanType(PlanType.PREMIUM);
            plan.setMonths(3);
            plan.setCost(300);
        }
    }
}
