package com.github.phasebash.katas.vendingmachine.core.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Common coins.
 */
public class Coins {

    private static final Coin QUARTER = Coin.builder().recognized(true).description("quarter").value(25).build();
    private static final Coin DIME    = Coin.builder().recognized(true).description("dime").value(10).build();
    private static final Coin NICKLE  = Coin.builder().recognized(true).description("nickle").value(5).build();

    public static Coin quarter() {
        return QUARTER;
    }

    public static Coin dime() {
        return DIME;
    }

    public static Coin nickle() {
        return NICKLE;
    }

    public static List<Coin> quarter(final int count) {
        return makeThisMany(count, Coins.quarter());
    }

    public static List<Coin> dime(final int count) {
        return makeThisMany(count, Coins.dime());
    }

    public static List<Coin> nickle(final int count) {
        return makeThisMany(count, Coins.nickle());
    }

    private static List<Coin> makeThisMany(final int count, final Coin coin) {
        final List<Coin> coins = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            coins.add(coin);
        }

        return coins;
    }

}
