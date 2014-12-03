package com.github.phasebash.katas.vendingmachine.core.bank

import org.junit.Before
import org.junit.Test

/**
 * Tests of the DefaultPaymentCalculator.
 */
class PaymentCalculatorTest {

    private DefaultPaymentCalculator calculator

    @Before
    void setUp() {
        calculator = new DefaultPaymentCalculator()
    }

    @Test
    void 'should properly calculate a simple case'() {
        List<Coin> coins =
                calculator.calculatePayment(50, [Coins.dime(), Coins.nickle(), Coins.quarter(), Coins.dime(), Coins.dime(), Coins.quarter()])

        def expected = values(coins)

        assert expected == [25, 10, 10, 5]
    }

    @Test(expected = InsufficientFundsException)
    void 'should throw exception when payment cannot be calculated'() {
        calculator.calculatePayment(90, [Coins.dime()])
    }

    @Test
    void 'should properly calculate change from available bank state'() {
        def expected = [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5]
        def state = new BankState(3, 100, 1)

        assert expected == values(calculator.calculateChange(105, state))
    }

    @Test(expected = InsufficientChangeException)
    void 'should throw exception when change cannot be calculated'() {
        calculator.calculateChange(50, new BankState(1, 1, 1))
    }

    private List<Integer> values(List<Coin> coins) {
        coins.collect { it.value }
    }
}

