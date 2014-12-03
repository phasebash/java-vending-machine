package com.github.phasebash.katas.vendingmachine.core.bank

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test

/**
 * Tests of the Bank.
 */
@WithGMock
class BankTest {

    private Bank bank

    private PaymentCalculator paymentCalculator

    @Before
    void setUp() {
        paymentCalculator = mock(PaymentCalculator)

        bank = new Bank(10, 10, 10, paymentCalculator)
    }

    @Test
    void 'should calculate bank balance correctly'() {
        assert bank.calculateBankBalance() == 400
    }

    @Test
    void 'should contain balance for coins offered'() {
        play {
            bank.offerCoins([Coins.quarter(), Coins.dime()])

            assert bank.currentBalance() == 35
        }
    }

    @Test
    void 'should have idempotent current balance'() {
        play {
            bank.offerCoins([Coins.quarter(), Coins.nickle()])

            assert bank.currentBalance() == bank.currentBalance()
        }
    }

    @Test(expected = InsufficientFundsException)
    void 'should now allow payment when current balance is insufficient'() {
        play {
            bank.pay(50)
        }
    }

    @Test
    void 'should be able to make change from the bank'() {
        bank = new Bank(10, 0, 10, paymentCalculator)

        def coins = [Coins.quarter(), Coins.dime()]

        paymentCalculator.calculatePayment(10, coins).returns([Coins.dime()])
        paymentCalculator.calculateChange(10, new BankState(10, 0, 10)).returns([Coins.dime()])

        play {
            bank.offerCoins([Coins.quarter(), Coins.dime()])

            assert [Coins.dime()] == bank.pay(25)

            assert bank.calculateBankBalance() == 310
            assert bank.currentBalance() == 0
        }
    }

    // integration test.
    @Test
    void 'should integrate properly with payment calculator and offer correct change'() {
        def coins = [Coins.quarter(), Coins.quarter(), Coins.dime()]

        paymentCalculator.calculatePayment(10, coins).returns([Coins.dime()])
        paymentCalculator.calculateChange(10, new BankState(10, 10, 10)).returns([Coins.dime()])

        play {
            bank.offerCoins(coins)

            List<Coin> change = bank.pay(50)

            assert change == [Coins.dime()]
        }
    }

}
