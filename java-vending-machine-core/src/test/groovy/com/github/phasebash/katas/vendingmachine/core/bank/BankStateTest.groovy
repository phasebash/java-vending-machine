package com.github.phasebash.katas.vendingmachine.core.bank

import org.junit.Test

/**
 * Tests of the BankState POJO methods.
 */
class BankStateTest {

    @Test
    void 'should properly convert a state into coins'() {
        assert new BankState(1, 1, 1).asCoins() == [Coins.quarter(), Coins.dime(), Coins.nickle()]
    }

    @Test
    void 'should properly convert a state into coins with a few missing'() {
        assert new BankState(1, 0, 0).asCoins() == [Coins.quarter()]
    }

    @Test
    void 'should calculate sum with quarters'() {
        assert new BankState(1, 0, 0).sum() == 25
    }

    @Test
    void 'should calculate sum with dimes'() {
        assert new BankState(0, 1, 0).sum() == 10
    }

    @Test
    void 'should calculate sum with nickles'() {
        assert new BankState(0, 0, 1).sum() == 5
    }

    @Test
    void 'should calculate sum with all those mofos'() {
        assert new BankState(2, 2, 2).sum() == 80
    }

    @Test
    void 'should calculate addition between states'() {
        assert new BankState(1, 0, 5).plus(new BankState(1, 5, 0)) == new BankState(2, 5, 5)
    }

    @Test
    void 'should calculate subtraction between states'() {
        assert new BankState(5, 5, 5).minus(new BankState(1, 2, 3)) == new BankState(4, 3, 2)
    }
}
