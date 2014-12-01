package com.github.phasebash.katas.vendingmachine.core.bank

import org.junit.Test

/**
 * Tests of the Coins static helpers.
 */
class CoinsTest {

    @Test
    void 'should provide a quarters helper'() {
        assert Coins.quarter().value == 25
    }

    @Test
    void 'should provide a dimes helper'() {
        assert Coins.dime().value == 10
    }

    @Test
    void 'should provide a nickles helper'() {
        assert Coins.nickle().value == 5
    }

    @Test
    void 'should be able to create n quarters'() {
        assert Coins.quarter(2) == [Coins.quarter(), Coins.quarter()]
    }

    @Test
    void 'should be able to create n dimes'() {
        assert Coins.dime(2) == [Coins.dime(), Coins.dime()]
    }

    @Test
    void 'should be able to create n nickles'() {
        assert Coins.nickle(2) == [Coins.nickle(), Coins.nickle()]
    }

}
