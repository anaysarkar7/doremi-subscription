package com.example.geektrust.bo;

import com.example.geektrust.bo.subscriptions.MusicSubscription;
import com.example.geektrust.bo.subscriptions.PodcastSubscription;
import com.example.geektrust.bo.subscriptions.Subscription;
import com.example.geektrust.bo.subscriptions.VideoSubscription;
import com.example.geektrust.constants.SubscriptionType;
import com.example.geektrust.constants.TopupType;

import java.util.*;

import static com.example.geektrust.constants.Constants.NO_OF_DAYS_PER_MONTH;
import static com.example.geektrust.constants.ErrorCode.*;
import static com.example.geektrust.constants.TopupType.FOUR_DEVICE;
import static com.example.geektrust.constants.TopupType.TEN_DEVICE;

public class User {
    private TopupType topupType;
    private int noOfDevices;
    private int renewalAmount;
    private Date subscriptionDate;
    private List<Subscription> subscriptionList;
    private final Map<SubscriptionType, Date> subscriptionRenewalDate;

    public User() {
        this.subscriptionList = new ArrayList<>();
        this.subscriptionRenewalDate = new HashMap<>();
    }

    public Map<SubscriptionType, Date> getSubscriptionRenewalDate() {
        return this.subscriptionRenewalDate;
    }

    public void addTopup(String topupType, int count) {
        if (subscriptionList.size() == 0) return;
        if (this.topupType != null) {
            System.out.println(ADD_TOPUP_FAILED.name() + " " + DUPLICATE_TOPUP.name());
        }
        if (topupType.equals(FOUR_DEVICE.name())) {
            this.renewalAmount += (FOUR_DEVICE.costPerMonth * count);
            this.topupType = FOUR_DEVICE;
        } else if (topupType.equals(TEN_DEVICE.name())) {
            this.renewalAmount += (TEN_DEVICE.costPerMonth * count);
            this.topupType = TEN_DEVICE;
        }
    }

    public void addSubscription(String subscriptionType, String planType) {
        Subscription subscription;
        if (Objects.equals(subscriptionType, SubscriptionType.MUSIC.name())) {
            subscription = new MusicSubscription();
        } else if (Objects.equals(subscriptionType, SubscriptionType.VIDEO.name())) {
            subscription = new VideoSubscription();
        } else {
            subscription = new PodcastSubscription();
        }
        if (this.subscriptionRenewalDate.containsKey(subscription.getSubscriptionType())) {
            System.out.println(ADD_SUBSCRIPTION_FAILED.name() + " " + DUPLICATE_CATEGORY.name());
        }
        subscription.setPlan(planType);
        this.subscriptionList.add(subscription);

        this.renewalAmount += subscription.getPlan().getCost();

        Calendar c = Calendar.getInstance();
        c.setTime(this.subscriptionDate);
        c.add(Calendar.DATE, subscription.getPlan().getMonths() * NO_OF_DAYS_PER_MONTH - 10);
        Date renewalDate = c.getTime();
        this.subscriptionRenewalDate.put(subscription.getSubscriptionType(), renewalDate);
    }

    public TopupType getTopupType() {
        return topupType;
    }

    public int getNoOfDevices() {
        return noOfDevices;
    }

    public void setNoOfDevices(int noOfDevices) {
        this.noOfDevices = noOfDevices;
    }

    public int getRenewalAmount() {
        return renewalAmount;
    }

    public void setRenewalAmount(int renewalAmount) {
        this.renewalAmount = renewalAmount;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
