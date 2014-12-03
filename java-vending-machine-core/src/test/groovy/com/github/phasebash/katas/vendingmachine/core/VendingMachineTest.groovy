package com.github.phasebash.katas.vendingmachine.core

import com.github.phasebash.katas.vendingmachine.core.bank.Bank
import com.github.phasebash.katas.vendingmachine.core.display.Display
import com.github.phasebash.katas.vendingmachine.core.input.CoinParser
import com.github.phasebash.katas.vendingmachine.core.output.CoinReturn
import com.github.phasebash.katas.vendingmachine.core.product.Inventory
import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test

/**
 * Tests of the VendingMachine class.
 */
@WithGMock
class VendingMachineTest {

    // class under tests.
    private VendingMachine machine

    // collaborators
    private Inventory inventory
    private Bank bank
    private Display display
    private CoinReturn coinReturn
    private CoinParser coinParser

    @Before
    void setUp() {
        inventory = mock(Inventory)
        bank = mock(Bank)
        display = mock(Display)
        coinReturn = mock(CoinReturn)
        coinParser = mock(CoinParser)

        machine = new VendingMachine(inventory, bank, display, coinReturn, coinParser);
    }

    @Test
    void 'should update display with current amount when valid coin is inserted'() {

    }

    @Test
    void 'should display INSERT COIN when no coins are inserted'() {

    }

    @Test
    void 'should put rejected coins in the coin return'() {

    }

    @Test
    void 'should dispense product and thank customer when sufficient money is inserted and product selected'() {

    }

    @Test
    void 'should not dispense product and thank customer when insufficient money is inserted and product selected'() {

    }

    @Test
    void 'should return change to user when product is selected and extra coinage given'() {

    }

    @Test
    void 'should display sold out message when product lacks stock and product selected'() {

    }

    @Test
    void 'should require exact change when bank cannot make change'() {

    }
}
