package com.inqoo.quality.clean.example.bad;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
WTF?
BIG FAT FAIL!
 */
public class Calc {
    // gets tax value for given base income
    public BigDecimal get(BigDecimal value) {
        BigDecimal res = BigDecimal.ZERO;
        if (value.doubleValue() <= 8000) {
            res =  BigDecimal.ZERO;
        } else if (exceedsFirstTaxThreshold(value) && value.doubleValue() <= 13000) {
            res =  BigDecimal.valueOf(value.doubleValue() * 0.17 - (1360 - 834.88 * (value.doubleValue() - 8000) / 5000)).setScale(0, RoundingMode.HALF_UP);
        } else if (exceedsFirstTaxThreshold(value) && value.doubleValue() > 13000 && value.doubleValue() <= 85528) {
            res =  BigDecimal.valueOf(value.doubleValue() * 0.17 - 525.12).setScale(0, RoundingMode.HALF_UP);

        // drugi próg
        } else if (exeedsSecondTaxThreshold(value) && value.doubleValue() <= 127000) {
            res =  BigDecimal.valueOf(85528 * 0.17 + (value.doubleValue() - 85528) * 0.32 - (525.12 - (525.12 * (value.doubleValue() - 85528)) / 41472)).setScale(0, RoundingMode.HALF_UP);
        } else if (exeedsSecondTaxThreshold(value) &&value.doubleValue() > 127000 && value.doubleValue() <= 1000000) {
            res =  BigDecimal.valueOf(85528 * 0.17 + (value.doubleValue() - 85528) * 0.32).setScale(0, RoundingMode.HALF_UP);
        } else if(exceedsSolidatiryTaxThreshold(value)) {
            res =  BigDecimal.valueOf(85528 * 0.17 + (value.doubleValue() - 85528) * 0.32 + (value.doubleValue() - 1000000) * 0.04).setScale(0, RoundingMode.HALF_UP);
        }
        return res;
    }

    private boolean exceedsSolidatiryTaxThreshold(BigDecimal value) {
        return value.doubleValue() > 1000000;
    }

    private boolean exeedsSecondTaxThreshold(BigDecimal value) {
        return value.doubleValue() > 85528;
    }

    private boolean exceedsFirstTaxThreshold(BigDecimal value) {
        return value.doubleValue() > 8000;
    }
}