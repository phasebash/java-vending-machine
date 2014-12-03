package com.github.phasebash.katas.vendingmachine.core.bank;

import lombok.Value;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The state of a bank's monies.
 */
@Value
public class BankState {

    private long numberOfQuarters;
    private long numberOfDimes;
    private long numberOfNickles;

    /**
     * Build the total value of this state.
     *
     * @return The total value in cents.
     */
    public int sum() {
        return (Coins.quarter().getValue() * (int) numberOfQuarters) +
                (Coins.dime().getValue() * (int) numberOfDimes) +
                (Coins.nickle().getValue() * (int) numberOfNickles);
    }

    /**
     * Subtract a state from this one and return a new state as the difference.
     *
     * @param bankState The right hand side.
     * @return The difference.
     */
    public BankState minus(final BankState bankState) {
        return new BankState(
            this.numberOfQuarters     - bankState.getNumberOfQuarters(),
            this.getNumberOfDimes()   - bankState.getNumberOfDimes(),
            this.getNumberOfNickles() - bankState.getNumberOfNickles()
        );
    }

    /**
     * Add a state to this one and return.
     *
     * @param bankState The right hand side.
     * @return The sum.
     */
    public BankState plus(final BankState bankState) {
        return new BankState(
                this.numberOfQuarters     + bankState.getNumberOfQuarters(),
                this.getNumberOfDimes()   + bankState.getNumberOfDimes(),
                this.getNumberOfNickles() + bankState.getNumberOfNickles()
        );
    }

    /**
     * Utility method to return this state as a list of coins.
     *
     * @return The list of coins.
     */
    public List<Coin> asCoins() {
        final List<Coin> coins = new LinkedList<>();

        coins.addAll(Coins.quarter((int) numberOfQuarters));
        coins.addAll(Coins.dime((int)    numberOfDimes));
        coins.addAll(Coins.nickle((int)  numberOfNickles));

        return coins;
    }

    public static BankState fromCoins(final List<Coin> input) {
        final Map<Coin, Long> distribution = input.stream().collect(Collectors.groupingBy(coin -> coin, Collectors.counting()));
        final BankState state = new BankState(
                get(distribution, Coins.quarter()),
                get(distribution, Coins.dime()),
                get(distribution, Coins.nickle())
        );

        return state;
    }

    private static int get(final Map<Coin, Long> map, final Coin key) {
        final Long value = map.get(key);

        if (value == null) {
            return 0;
        }

        return value.intValue();
    }
}
