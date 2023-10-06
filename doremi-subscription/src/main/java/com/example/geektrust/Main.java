package com.example.geektrust;

import com.example.geektrust.bo.subscriptions.MusicSubscription;
import com.example.geektrust.bo.subscriptions.PodcastSubscription;
import com.example.geektrust.bo.subscriptions.Subscription;
import com.example.geektrust.bo.User;
import com.example.geektrust.constants.Commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Scanner;

import static com.example.geektrust.constants.ErrorCode.*;
import static com.example.geektrust.constants.Filepaths.*;
import static com.example.geektrust.constants.Formats.DATE_FORMAT;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(input1);
        Scanner scanner = new Scanner(fileInputStream);
        User user = null;
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            //START_SUBSCRIPTION
            if (Objects.equals(command, Commands.START_SUBSCRIPTION.name())) {
                String startDateString = scanner.next();
                user = new User();
                try {
                    user.setSubscriptionDate(new SimpleDateFormat(DATE_FORMAT).parse(startDateString));
                } catch (ParseException e) {
                    System.out.println(INVALID_DATE.name());
                }
            }
            //ADD_SUBSCRIPTION
            if (Objects.equals(command, Commands.ADD_SUBSCRIPTION.name())) {
                if (user == null) {
                    System.out.println(ADD_SUBSCRIPTION_FAILED.name() + " " + SUBSCRIPTIONS_NOT_FOUND.name());
                    continue;
                }
                String subscriptionType = scanner.next();
                String planType = scanner.next();
                user.addSubscription(subscriptionType, planType);
            }
            //ADD_TOPUP
            if (Objects.equals(command, Commands.ADD_TOPUP.name())) {
                if (user == null) {
                    System.out.println(ADD_TOPUP_FAILED.name() + " " + SUBSCRIPTIONS_NOT_FOUND.name());
                    continue;
                }
                String topupType = scanner.next();
                String countString = scanner.next();
                int count = Integer.parseInt(countString);
                user.addTopup(topupType, count);
            }
            //PRINT_RENEWAL_DETAILS
            if (Objects.equals(command, Commands.PRINT_RENEWAL_DETAILS.name())) {
                if (user == null) {
                    System.out.println(SUBSCRIPTIONS_NOT_FOUND.name());
                    continue;
                }
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                if (user.getSubscriptionList().size() == 0) {
                    System.out.println(SUBSCRIPTIONS_NOT_FOUND.name());
                }
                for (Subscription subscription : user.getSubscriptionList()) {
                    System.out.println("RENEWAL_REMINDER"
                            + " "
                            + subscription.getSubscriptionType().name()
                            + " "
                            + sdf.format(user.getSubscriptionRenewalDate().get(subscription.getSubscriptionType()))

                    );
                }
                System.out.println("RENEWAL_AMOUNT " + user.getRenewalAmount());
            }
        }
        scanner.close();
    }
}
