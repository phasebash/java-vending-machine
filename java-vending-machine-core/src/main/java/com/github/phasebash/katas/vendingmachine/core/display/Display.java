package com.github.phasebash.katas.vendingmachine.core.display;

/**
 * Interface for an implementation that can act as a display for a vending machine.
 */
public interface Display {

    /**
     * Update the display with the given message.
     *
     * @param message The message.
     */
    void displayText(String message);

    /**
     * Update the display with the given message as currency amount.
     *
     * @param amount The amount
     */
    void displayCurrency(int amount);

}
