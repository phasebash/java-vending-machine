package com.github.phasebash.katas.vendingmachine.core.bank;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A class that contains abstractions for dealing with a balance, making change, and debiting.
 */
public class Bank {

    private static final PaymentCalculator PAYMENT_CALCULATOR = new PaymentCalculator();

    // the coins that have been offered for payment.
    private final List<Coin> offered;

    // the coins this bank has with count.
    private final Map<Coin, Integer> bank;

    // the total monies in the bank.
    private Integer bankBalance = 0;

    public Bank(final int quarters, final int dimes, final int nickles) {
        this.bank = new HashMap<>();
        this.offered = new LinkedList<>();

        this.bank.put(Coins.quarter(), quarters);
        this.bank.put(Coins.dime(), dimes);
        this.bank.put(Coins.nickle(), nickles);
    }

    /**
     * Offer the given coins to the bank.
     *
     * @param coins The coins.
     */
    public void offerCoins(final List<Coin> coins) {
        offered.addAll(coins);
    }

    /**
     * Pay out the offered monies and receive change.
     *
     * @return The change.
     */
    public List<Coin> pay(final int cost) {
        final int balance = currentBalance();

        if (balance < cost) {
            throw new InsufficientFundsException();
        }

        final List<Coin> extras = acceptCoins(balance - cost, offered);

        bankBalance += cost;

        offered.clear();

        return extras;
    }

    private List<Coin> acceptCoins(final int cost, final List<Coin> coins) {
        return PAYMENT_CALCULATOR.calculatePayment(cost, coins);
    }

    public int currentBalance() {
        return calculateSum(offered);
    }

    public int getBankBalance() {
        return bankBalance;
    }

    private int calculateSum(final List<Coin> coins) {
        return coins.stream().mapToInt(Coin::getValue).sum();
    }
}
