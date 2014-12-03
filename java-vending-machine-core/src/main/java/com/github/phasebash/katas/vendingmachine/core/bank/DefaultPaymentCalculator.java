package com.github.phasebash.katas.vendingmachine.core.bank;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that uses a napsack filling algorithm to pay out the bank.
 */
public class DefaultPaymentCalculator implements PaymentCalculator {

    public List<Coin> calculatePayment(final int cost, final List<Coin> input) {
        final BankState bankState = BankState.fromCoins(input);
        final List<Coin> output = searchForBestFit(cost, bankState);

        if (output == null) {
            // the algorithm above will always halt and return a solution if there is one to be found, or will
            // halt with no solution if none exists.
            throw new InsufficientFundsException();
        }

        return output;
    }

    public List<Coin> calculateChange(final int cost, final BankState bankState) {
        final List<Coin> output = searchForBestFit(cost, bankState);

        if (output == null) {
            // the algorithm above will always halt and return a solution if there is one to be found, or will
            // halt with no solution if none exists.
            throw new InsufficientChangeException();
        }

        return output;
    }

    private List<Coin> searchForBestFit(final int amount, final BankState bankState) {
        final Deque<BankState> stack = new LinkedList<>(Arrays.asList(new BankState(0, 0, 0)));

        while (!stack.isEmpty()) {
            final BankState candidate = stack.removeLast();

            if (candidate.sum() == amount) {
                return candidate.asCoins();
            }

            stack.addAll(validStates(nextStates(candidate), bankState));
        }

        return null;
    }


    private List<BankState> nextStates(final BankState state) {
        return Arrays.asList(
                new BankState(state.getNumberOfQuarters() + 1, state.getNumberOfDimes(),     state.getNumberOfNickles()),
                new BankState(state.getNumberOfQuarters(),     state.getNumberOfDimes() + 1, state.getNumberOfNickles()),
                new BankState(state.getNumberOfQuarters(),     state.getNumberOfDimes(),     state.getNumberOfNickles() + 1));
    }

    private List<BankState> validStates(final List<BankState> states, final BankState maximalState) {
        return states.stream().filter(state -> state.getNumberOfQuarters() <= maximalState.getNumberOfQuarters() &&
                        state.getNumberOfDimes() <= maximalState.getNumberOfDimes() &&
                        state.getNumberOfNickles() <= maximalState.getNumberOfNickles()
        ).collect(Collectors.toList());
    }

}
