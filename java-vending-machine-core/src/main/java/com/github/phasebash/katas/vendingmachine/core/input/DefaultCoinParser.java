package com.github.phasebash.katas.vendingmachine.core.input;

import com.github.phasebash.katas.vendingmachine.core.bank.Coin;
import com.github.phasebash.katas.vendingmachine.core.bank.Coins;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A default implementation of a coin parser.
 */
public class DefaultCoinParser implements CoinParser {

    private static final Map<String, Coin> SUPPORTED_COIN_MAP = new HashMap<>();

    static {
        SUPPORTED_COIN_MAP.put("$0.05", Coins.nickle());
        SUPPORTED_COIN_MAP.put("$0.10", Coins.dime());
        SUPPORTED_COIN_MAP.put("$0.25", Coins.quarter());
    }

    /**
     * Parse coin strings.  Coins are in the form $0.25, $0.05, and $0.10.  All other tokens are considered
     * UNSUPPORTED.
     *
     * @param coinString The uncontrolled coin string.
     * @return The list of Coin enum values.  Always immutable.
     */
    @Override
    public @NonNull
    List<Coin> parseCoins(@NonNull final String coinString) {
        final String trimmed = coinString.trim();

        if ("".equals(trimmed)) {
            return Collections.emptyList();
        }

        final List<Coin> coinz =
                Arrays.asList(coinString.trim().split("\\s+"))
                        .stream().map(this::findCoinType).collect(Collectors.toList());

        return Collections.unmodifiableList(coinz);
    }

    private Coin findCoinType(final String centValue) {
        Coin coin = SUPPORTED_COIN_MAP.get(centValue);

        if (coin == null) {
            coin = Coin.builder().value(0).recognized(false).description(centValue).build();
        }

        return coin;
    }

}
