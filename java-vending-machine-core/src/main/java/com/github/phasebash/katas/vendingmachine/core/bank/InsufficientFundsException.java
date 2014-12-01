package com.github.phasebash.katas.vendingmachine.core.bank;

/**
 * An exception thrown when insufficient funds are present for a given operation.
 */
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
       super();
    }

}
