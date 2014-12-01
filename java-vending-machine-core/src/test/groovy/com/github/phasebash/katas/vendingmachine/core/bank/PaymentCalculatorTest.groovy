package com.github.phasebash.katas.vendingmachine.core.bank

import org.junit.Before
import org.junit.Test

/**
 * Tests of the PaymentCalculator.
 */
class PaymentCalculatorTest {

    private PaymentCalculator calculator

    @Before
    void setUp() {
        calculator = new PaymentCalculator()
    }

    @Test
    void 'should properly calculate a simple case'() {
        List<Coin> coins =
                calculator.calculatePayment(50, [Coins.dime(), Coins.nickle(), Coins.quarter(), Coins.dime(), Coins.dime(), Coins.quarter()])

        assert coins == [Coins.quarter(), Coins.quarter()]
    }

    @Test(expected = InsufficientFundsException)
    void 'should throw exception when payment cannot be calculated'() {
            calculator.calculatePayment(90, [Coins.dime()])
    }
}

