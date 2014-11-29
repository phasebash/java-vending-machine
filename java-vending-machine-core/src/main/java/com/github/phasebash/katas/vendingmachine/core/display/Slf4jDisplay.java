package com.github.phasebash.katas.vendingmachine.core.display;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * An implementation of Display that logs.
 */
@Slf4j
public class Slf4jDisplay implements Display {

    @Override
    public void update(final @NonNull String message) {
        log.info(message);
    }

}
