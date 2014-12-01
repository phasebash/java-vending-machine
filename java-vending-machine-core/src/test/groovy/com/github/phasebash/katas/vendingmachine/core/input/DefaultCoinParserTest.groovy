package com.github.phasebash.katas.vendingmachine.core.input

import com.github.phasebash.katas.vendingmachine.core.bank.Coin
import com.github.phasebash.katas.vendingmachine.core.bank.Coins
import org.junit.Before
import org.junit.Test

/**
 * Tests of the DefaultCoinParser.
 */
class DefaultCoinParserTest {

    private static final Coin unsupported = Coin.builder().value(0).recognized(false).build();

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
        input('').add(Coins.dime())
    }

    @Test
    void 'should parse a dime'() {
        assert input('$0.10') == [Coins.dime()]
    }

    @Test
    void 'should parse a dime with space'() {
        assert input('   $0.10   ') == [Coins.dime()]
    }

    @Test
    void 'should parse a nickle'() {
        assert input('   $0.05   ') == [Coins.nickle()]
    }

    @Test
    void 'should parse a quarter'() {
        assert input('   $0.25   ') == [Coins.quarter()]
    }

    @Test
    void 'should parse mixed input'() {
        assert input('  $1.00 $x.9x $ 0.25 $0.25') == [unsupported, unsupported, unsupported, unsupported, Coins.quarter()]
    }

    private List<Coin> input(final String value) {
        coinParser.parseCoins(value)
    }


}
