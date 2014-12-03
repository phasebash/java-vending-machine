package com.github.phasebash.katas.vendingmachine.core.output;

import com.github.phasebash.katas.vendingmachine.core.bank.Coin;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * A simple coin returner that just logs.
 */
@Slf4j
public class DefaultCoinReturn implements CoinReturn {

    /**
     * Return coins to user.
     * @param coins The invalid or unsupported coins.
     */
    public void returnCoins(final @NonNull List<Coin> coins) {
        log.info("returning coins: {}", coins);
    }

}
