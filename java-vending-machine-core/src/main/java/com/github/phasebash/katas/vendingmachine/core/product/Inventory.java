package com.github.phasebash.katas.vendingmachine.core.product;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A container object for the current inventory state of a vending machine.
 */
public class Inventory {

    private final Map<ProductCode, Integer> inventory = new HashMap<>();

    public Product findProduct(final @NonNull ProductCode productCode) {
       return null;
    }
}
