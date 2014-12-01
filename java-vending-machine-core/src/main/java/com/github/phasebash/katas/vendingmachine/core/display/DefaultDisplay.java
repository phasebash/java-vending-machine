package com.github.phasebash.katas.vendingmachine.core.display;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

/**
 * An implementation of Display that logs.
 */
@Slf4j
public class DefaultDisplay implements Display {

    private static final DecimalFormat FORMAT = new DecimalFormat("#.##");

    @Override
    public void displayText(final @NonNull String message) {
        log.info(message);
    }

    @Override
    public void displayCurrency(final int amount) {
        log.info(FORMAT.format(amount * 0.01F));
    }

}
