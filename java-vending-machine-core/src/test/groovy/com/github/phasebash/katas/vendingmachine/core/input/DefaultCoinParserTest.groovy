package com.github.phasebash.katas.vendingmachine.core.input

import com.github.phasebash.katas.vendingmachine.core.bank.Coin;
import org.junit.Before;
import org.junit.Test

import static com.github.phasebash.katas.vendingmachine.core.bank.Coin.dime
import static com.github.phasebash.katas.vendingmachine.core.bank.Coin.nickle
import static com.github.phasebash.katas.vendingmachine.core.bank.Coin.quarter
import static com.github.phasebash.katas.vendingmachine.core.bank.Coin.unsupported;

/**
 * Tests of the DefaultCoinParser.
 */
class DefaultCoinParserTest {

    private CoinParser coinParser;

    @Before
    void setUp() {
        coinParser = new DefaultCoinParser();
    }

    @Test(expected = NullPointerException)
    void 'should not allow null input'() {
        input(null)
    }

    @Test
    void 'should have empty coins on empty input'() {
        assert input('') == []
    }

    @Test(expected = UnsupportedOperationException)
    void 'should have immutable coins on empty input'(){
        input('').add(dime)
    }

    @Test
    void 'should parse a dime'() {
        assert input('$0.10') == [dime]
    }

    @Test
    void 'should parse a dime with space'() {
        assert input('   $0.10   ') == [dime]
    }

    @Test
    void 'should parse a nickle'() {
        assert input('   $0.05   ') == [nickle]
    }

    @Test
    void 'should parse a quarter'() {
        assert input('   $0.25   ') == [quarter]
    }

    @Test
    void 'should parse mixed input'() {
        assert input('  $1.00 $x.9x $ 0.25 $0.25') == [unsupported, unsupported, unsupported, unsupported, quarter]
    }

    private List<Coin> input(final String value) {
        coinParser.parseCoins(value)
    }

}
