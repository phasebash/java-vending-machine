package com.github.phasebash.katas.vendingmachine.core.input;

import com.github.phasebash.katas.vendingmachine.core.bank.Coin;
import lombok.NonNull;

import java.util.List;

/**
 * Interface for an implementation that can parse coin strings and return the relevant coin objects.
 */
public interface CoinParser {

    /**
     * Parse the given coin string into a List of Coin objects.
     *
     * @param coinString The uncontrolled coin string.
     * @return The legit list of coins.  Note: invalid coins should all be represented as UNSUPPORTED.
     */
    @NonNull
    List<Coin> parseCoins(String coinString);
}
