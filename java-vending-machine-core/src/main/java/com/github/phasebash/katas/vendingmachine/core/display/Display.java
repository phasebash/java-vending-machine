package com.github.phasebash.katas.vendingmachine.core.display;

import lombok.NonNull;

/**
 * Interface for an implementation that can act as a display for a vending machine.
 */
public interface Display {

    /**
     * Update the display with the given message.
     *
     * @param message The message.
     */
    void update(@NonNull String message);

}
