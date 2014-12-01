package com.github.phasebash.katas.vendingmachine.core.bank;

import lombok.Value;
import lombok.experimental.Builder;

/**
 * The valid coin types.
 */
@Value
@Builder
public class Coin {

    private Integer value;

    private String description;

    private boolean recognized;

}
