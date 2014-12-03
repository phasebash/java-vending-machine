package com.github.phasebash.katas.vendingmachine.core.bank;

import java.util.List;

/**
 * An interface for an implementation that can calculate payments.
 */
public interface PaymentCalculator {

    List<Coin> calculatePayment(final int cost, final List<Coin> input);

    List<Coin> calculateChange(final int cost, final BankState bankState);

}
