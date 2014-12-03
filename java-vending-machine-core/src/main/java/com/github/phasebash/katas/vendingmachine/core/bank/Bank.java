package com.github.phasebash.katas.vendingmachine.core.bank;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that contains abstractions for dealing with a balance, making change, and debiting.
 */
public class Bank {

    // the coins that have been offered for payment.
    private final List<Coin> offered;

    private final PaymentCalculator paymentCalculator;

    // immutable.
    private BankState bankState;

    /**
     * Create a bank with initial bank capacities.
     *
     * @param quarters The number of quarters.
     * @param dimes The number of dimes.
     * @param nickles The number of nickles.
     */
    public Bank(final int quarters, final int dimes, final int nickles, final PaymentCalculator paymentCalculator) {
        this.offered   = new LinkedList<>();
        this.bankState = new BankState(quarters, dimes, nickles);
        this.paymentCalculator = paymentCalculator;
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

        assertFundsAreSufficient(cost, balance);

        final int extra = balance - cost;
        final List<Coin> bankedCoins = findPaidCoins(extra, offered);
        final List<Coin> change = findChange(extra);

        refreshBankState(bankedCoins);

        return change;
    }

    private void assertFundsAreSufficient(final int cost, final int balance) {
        if (balance < cost) {
            throw new InsufficientFundsException();
        }
    }

    private void refreshBankState(final List<Coin> bankedCoins) {
        bankState = bankState.plus(BankState.fromCoins(bankedCoins));
        offered.clear();
    }

    private List<Coin> findChange(int extra) {
        return paymentCalculator.calculateChange(extra, bankState);
    }

    private List<Coin> findPaidCoins(final int cost, final List<Coin> coins) {
        return paymentCalculator.calculatePayment(cost, coins);
    }

    public int currentBalance() {
        return calculateSum(offered);
    }

    public int calculateBankBalance() {
        return bankState.sum();
    }

    private int calculateSum(final List<Coin> coins) {
        return coins.stream().mapToInt(Coin::getValue).sum();
    }
}
