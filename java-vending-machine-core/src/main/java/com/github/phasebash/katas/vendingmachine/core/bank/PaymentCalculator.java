package com.github.phasebash.katas.vendingmachine.core.bank;

import lombok.Value;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class that uses a napsack filling algorithm to pay out the bank.
 */
public class PaymentCalculator {

    /**
     * Find a mutually beneficial payment scheme for the bank and the customer.   Provides optional coins the bank has
     * said it would prefer to have filled first.
     *
     * This method uses a brute-force approach to knapsack filling, which is finds a solution, but in
     * suboptimal time and space for large inputs.  Luckily, the problem space of this kata is very small, and thus
     * a naive bruce force approach does not have large values of n and thus the naivety pays off.
     *
     * If we start selling cars and continue to accept only coins, this will be problematic.
     *
     * @param cost The cost of the item.
     * @param input The inputs given by the customer.
     *
     * @return The used coins.
     */
    public List<Coin> calculatePayment(final int cost, final List<Coin> input) {
        final Map<Coin, Long> distribution = input.stream().collect(Collectors.groupingBy(coin -> coin, Collectors.counting()));
        final SearchState maximalState = new SearchState(get(distribution, Coins.quarter()),
                get(distribution, Coins.dime()), get(distribution, Coins.nickle()));

        final Deque<SearchState> stack = new LinkedList<>(Arrays.asList(new SearchState(0, 0, 0)));

        while (!stack.isEmpty()) {
            final SearchState candidate = stack.removeLast();

            if (candidate.sum() == cost) {
                return candidate.asCoins();
            }

            stack.addAll(validStates(nextStates(candidate), maximalState));
        }

        // the algorithm above will always halt and return a solution if there is one to be found, or will
        // halt with no solution if none exists.
        throw new InsufficientFundsException();
    }

    private List<SearchState> nextStates(final SearchState state) {
        return Arrays.asList(
                new SearchState(state.getNumberOfQuarters() + 1, state.getNumberOfDimes(),     state.getNumberOfNickles()),
                new SearchState(state.getNumberOfQuarters(),     state.getNumberOfDimes() + 1, state.getNumberOfNickles()),
                new SearchState(state.getNumberOfQuarters(),     state.getNumberOfDimes(),     state.getNumberOfNickles() + 1));
    }

    private List<SearchState> validStates(final List<SearchState> states, final SearchState maximalState) {
        return states.stream().filter(state -> state.getNumberOfQuarters() <= maximalState.getNumberOfQuarters() &&
                        state.getNumberOfDimes() <= maximalState.getNumberOfDimes() &&
                        state.getNumberOfNickles() <= maximalState.getNumberOfNickles()
        ).collect(Collectors.toList());
    }

    private int get(final Map<Coin, Long> map, final Coin key) {
        final Long value = map.get(key);

        if (value == null) {
            return 0;
        }

        return value.intValue();
    }

    /**
     * The search state object representing this given area in the search space.
     */
    @Value
    private static class SearchState {
        private long numberOfQuarters;
        private long numberOfDimes;
        private long numberOfNickles;

        public int sum() {
            return (Coins.quarter().getValue() * (int) numberOfQuarters) +
                    (Coins.dime().getValue() * (int) numberOfDimes) +
                    (Coins.dime().getValue() * (int) numberOfNickles);
        }

        public List<Coin> asCoins() {
            final List<Coin> coins = new LinkedList<>();

            coins.addAll(Coins.quarter((int) numberOfQuarters));
            coins.addAll(Coins.dime((int)    numberOfDimes));
            coins.addAll(Coins.nickle((int)  numberOfNickles));

            return coins;
        }
    }

}
