package com.github.phasebash.katas.vendingmachine.core;

import com.github.phasebash.katas.vendingmachine.core.bank.Bank;
import com.github.phasebash.katas.vendingmachine.core.bank.DefaultPaymentCalculator;
import com.github.phasebash.katas.vendingmachine.core.display.DefaultDisplay;
import com.github.phasebash.katas.vendingmachine.core.input.DefaultCoinParser;

/**
 * Convenience methods for creating vending machines, methods deal with necessary DI.
 */
public class VendingMachines {

    public static VendingMachine newInstance()  {
        final VendingMachine vendingMachine = new VendingMachine(
                new Bank(10, 100, 1000, new DefaultPaymentCalculator()),
                new DefaultDisplay(),
                new CoinReturn(),
                new DefaultCoinParser());

        return vendingMachine;
    }

}
