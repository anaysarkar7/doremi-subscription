package com.example.geektrust.bo.subscriptions;

import com.example.geektrust.bo.Plan;
import com.example.geektrust.constants.SubscriptionType;

public abstract class Subscription {
    protected Plan plan;
    protected SubscriptionType subscriptionType;

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public abstract void setPlan(String planType);

    public Plan getPlan() {
        return plan;
    }
}
