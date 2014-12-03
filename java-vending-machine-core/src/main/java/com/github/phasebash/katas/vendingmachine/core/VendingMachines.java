package com.github.phasebash.katas.vendingmachine.core;

import com.github.phasebash.katas.vendingmachine.core.bank.Bank;
import com.github.phasebash.katas.vendingmachine.core.bank.DefaultPaymentCalculator;
import com.github.phasebash.katas.vendingmachine.core.display.DefaultDisplay;
import com.github.phasebash.katas.vendingmachine.core.input.DefaultCoinParser;
import com.github.phasebash.katas.vendingmachine.core.output.DefaultCoinReturn;
import com.github.phasebash.katas.vendingmachine.core.product.Inventory;

/**
 * Convenience methods for creating vending machines, methods deal with necessary DI.
 */
public class VendingMachines {

    /**
     * Construct a vending machine that is ready to use.
     *
     * @return The vending machine.
     */
    public static VendingMachine newInstance()  {
        final VendingMachine vendingMachine = new VendingMachine(
                new Inventory(),
                new Bank(10, 100, 1000, new DefaultPaymentCalculator()),
                new DefaultDisplay(),
                new DefaultCoinReturn(),
                new DefaultCoinParser());

        return vendingMachine;
    }

}
