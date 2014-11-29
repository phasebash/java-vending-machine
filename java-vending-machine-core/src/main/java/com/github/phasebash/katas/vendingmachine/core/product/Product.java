package com.github.phasebash.katas.vendingmachine.core.product;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

/**
 * A POJO/Bean for a product.
 */
@Value
@Builder
public class Product {

    private @NonNull Integer costInCents;

    private @NonNull String description;

}
