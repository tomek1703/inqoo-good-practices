package com.inqoo.quality.clean.exercises.vendingmachine;

import java.math.BigDecimal;

public enum Coin {
    PENNY (new BigDecimal("0.01")),

    /** 5 cents coin */
    NICKEL (new BigDecimal("0.05")),

    /** 10 cents coin */
    DIME   (new BigDecimal("0.1")),

    /** 25 cents coin */
    QUARTER(new BigDecimal("0.25")),
    ;

    private BigDecimal value;

    Coin(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
