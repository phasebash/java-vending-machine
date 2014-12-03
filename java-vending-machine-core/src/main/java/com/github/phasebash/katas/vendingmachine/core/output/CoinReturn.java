package com.github.phasebash.katas.vendingmachine.core.output;

import com.github.phasebash.katas.vendingmachine.core.bank.Coin;

import java.util.List;

/**
 * An interface for an implementation that can return coins.
 */
public interface CoinReturn {

    /**
     * Return coins to user.
     * @param coins The invalid or unsupported coins.
     */
    void returnCoins(final List<Coin> coins);

}
