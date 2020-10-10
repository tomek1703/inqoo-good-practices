package com.inqoo.quality.clean.bad;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calc {
    public BigDecimal get(BigDecimal base) {
        BigDecimal res;
        if (base.doubleValue() <= 8000) {
            res =  BigDecimal.ZERO;
        } else if (base.doubleValue() > 8000 && base.doubleValue() <= 13000) {
            res =  BigDecimal.valueOf(base.doubleValue() * 0.17 -
                    (1360 - 834.88 * (base.doubleValue() - 8000) / 5000)).setScale(0, RoundingMode.HALF_UP);
        } else if (base.doubleValue() > 13000 && base.doubleValue() <= 85528) {
            res =  BigDecimal.valueOf(base.doubleValue() * 0.17 - 525.12).setScale(0, RoundingMode.HALF_UP);
        } else if (base.doubleValue() > 85528 && base.doubleValue() <= 127000) {
            res =  BigDecimal.valueOf(85528 * 0.17 + (base.doubleValue() - 85528) * 0.32 - (525.12 - (525.12 * (base.doubleValue() - 85528)) / 41472)).setScale(0, RoundingMode.HALF_UP);
        } else if (base.doubleValue() > 127000 && base.doubleValue() < 1000000) {
            res =  BigDecimal.valueOf(85528 * 0.17 + (base.doubleValue() - 85528) * 0.32).setScale(0, RoundingMode.HALF_UP);
        } else {
            res =  BigDecimal.valueOf(85528 * 0.17 + (base.doubleValue() - 85528) * 0.32 + (base.doubleValue() - 1000000) * 0.04).setScale(0, RoundingMode.HALF_UP);
        }
        return res;
    }
}
