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

    @Before
    void setUp() {
        bank = new Bank(0, 0, 0)
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

    @Test
    void 'should put paid coins in bank balance and remove current balance'() {
        play {
            bank.offerCoins([Coins.quarter(), Coins.quarter()])

            bank.pay(50)

            assert bank.bankBalance == 50
            assert bank.currentBalance() == 0
        }
    }

    @Test(expected = InsufficientFundsException)
    void 'should now allow payment when current balance is insufficient'() {
        play {
            bank.pay(50)
        }
    }

    // integration test.
    @Test
    void 'should integrate properly with payment calculator and offer correct change'() {
        play {
            bank.offerCoins([Coins.quarter(), Coins.quarter(), Coins.dime()])

            List<Coin> coins = bank.pay(50)

            assert coins == [Coins.dime()]
        }
    }

}
