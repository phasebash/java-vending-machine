package com.github.phasebash.katas.vendingmachine;

import com.github.phasebash.katas.vendingmachine.core.VendingMachine;
import com.github.phasebash.katas.vendingmachine.core.VendingMachines;
import lombok.extern.slf4j.Slf4j;

/**
 * The main demonstration of the vending machine kata.
 */
@Slf4j
public class VendingMachineDemo {

    private static final VendingMachine vm = VendingMachines.newInstance();

    public static void main(String[] args) {
        log.info("welcome to the java vending machine kata demo");

        vm.acceptCoins("$0.11 $0.25 $0.25 $0.10 $0.05 ");
        vm.selectProduct("candy");
    }

}
