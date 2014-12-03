package com.github.phasebash.katas.vendingmachine.core;

import com.github.phasebash.katas.vendingmachine.core.bank.Bank;
import com.github.phasebash.katas.vendingmachine.core.bank.Coin;
import com.github.phasebash.katas.vendingmachine.core.display.Display;
import com.github.phasebash.katas.vendingmachine.core.input.CoinParser;
import com.github.phasebash.katas.vendingmachine.core.output.CoinReturn;
import com.github.phasebash.katas.vendingmachine.core.product.Inventory;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of a vending machine in Java.
 */
public class VendingMachine {

    private final Inventory inventory;

    private final Bank bank;

    private final CoinParser coinParser;

    private final CoinReturn coinReturn;

    private final Display display;

    // private final DisplayBusinessLogic displayBusinessLogic;

    public VendingMachine(
                          @NonNull final Inventory inventory,
                          @NonNull final Bank bank,
                          @NonNull final Display display,
                          @NonNull final CoinReturn coinReturn,
                          @NonNull final CoinParser coinParser) {
        this.inventory  = inventory;
        this.bank       = bank;
        this.display    = display;
        this.coinParser = coinParser;
        this.coinReturn = coinReturn;

        initialState(display);
    }

    public void acceptCoins(final String coinString) {
        final List<Coin> coins = coinParser.parseCoins(coinString);

        final List<Coin> acceptable = coins.stream().filter(Coin::isRecognized).collect(Collectors.toList());
        final List<Coin> rejected   = coins.stream().filter(coin -> !coin.isRecognized()).collect(Collectors.toList());

        coinReturn.returnCoins(rejected);
        bank.offerCoins(acceptable);

        display.displayCurrency(bank.currentBalance());
    }

    public void selectProduct(final String productName) {
        throw new UnsupportedOperationException("not implemented");

        // final Product product = inventory.findProduct(productName);
        // if (ableToDispense(product)) {
        //  dispense(product);
        // else {
        //  notifyUndispensible(product);
        // }
    }

    private void initialState(Display display) {
        display.displayText("INSERT COIN");
    }

}
