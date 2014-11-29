package com.github.phasebash.katas.vendingmachine.core;

/**
 * Implementation of a vending machine in Java.
 */
public class VendingMachine {

    // private final Inventory inventory;
    // private final Bank bank;
    // private final paymentState paymentState;
    // private final CoinParser coinParser;

    // private final Display display;
    // private final DisplayBusinessLogic displayBusinessLogic;

    public VendingMachine() {
        updateDisplay();
    }

    public void acceptCoins(final String coinString) {
        // final Coins coins = coinParser.parse(coinString);
        // getActivePaymentState().add(coins);
    }

    public void selectProduct(final String productName) {
        // final Product product = inventory.findProduct(productName);
        // if (ableToDispense(product)) {
        //  dispense(product);
        // else {
        //  notifyUndispensible(product);
        // }
    }

    private void updateDisplay() {
        // final String displayString = displayBusinessLogic.compute(...);
        // display.update(displayString);
    }

}
