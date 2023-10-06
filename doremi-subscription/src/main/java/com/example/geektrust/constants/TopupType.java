package com.example.geektrust.constants;

public enum TopupType {
    FOUR_DEVICE(50),
    TEN_DEVICE(100);
    public final int costPerMonth;
    TopupType(int costPerMonth) {
        this.costPerMonth = costPerMonth;
    }
}
