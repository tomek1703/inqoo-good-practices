package com.inqoo.quality.clean.good;

import java.math.BigDecimal;

import static com.inqoo.quality.clean.good.BigDecimalComparison.is;
import static java.math.BigDecimal.*;
import static java.math.RoundingMode.HALF_UP;

public class TaxCalculator {

    private final BigDecimal firstTaxThreshold = valueOf(8000);
    private final BigDecimal secondTaxThreshold = valueOf(85528);
    private final BigDecimal solidarityTaxThreshold = valueOf(1000000);

    private final BigDecimal firstTaxRate = new BigDecimal("0.17");
    private final BigDecimal secondTaxRate = new BigDecimal("0.32");
    private final BigDecimal solidarityTaxRate = new BigDecimal("0.04");


    public BigDecimal calculate(BigDecimal taxBase) {
        BigDecimal calculatedTax;
        if (noTaxIsApplicable(taxBase)) {
            calculatedTax = ZERO;
        } else if (firstTaxRateIsApplicable(taxBase)) {
            calculatedTax = taxForFirstTaxThreshold(taxBase);
        } else if (secondTaxRateIsApplicable(taxBase)) {
            calculatedTax = taxForSecondTaxThreshold(taxBase);
        } else {
            calculatedTax = taxWithSolidarityTax(taxBase);
        }
        return calculatedTax.setScale(0, HALF_UP);
    }

    private boolean noTaxIsApplicable(BigDecimal taxBase) {
        return is(taxBase).notGreaterThan(firstTaxThreshold);
    }

    private boolean firstTaxRateIsApplicable(BigDecimal taxBase) {
        return is(taxBase).between(firstTaxThreshold).andInclusive(secondTaxThreshold);
    }

    private BigDecimal taxForFirstTaxThreshold(BigDecimal taxBase) {
        return taxBase.multiply(firstTaxRate)
                .subtract(taxDeduction(taxBase));
    }

    private boolean secondTaxRateIsApplicable(BigDecimal taxBase) {
        return is(taxBase).between(secondTaxThreshold).andInclusive(solidarityTaxThreshold);
    }

    private BigDecimal taxForSecondTaxThreshold(BigDecimal taxBase) {
        return secondTaxThreshold.multiply(firstTaxRate)
                .add((taxBase.subtract(secondTaxThreshold).multiply(secondTaxRate)))
                .subtract(taxDeduction(taxBase));
    }

    private BigDecimal taxDeduction(BigDecimal taxBase) {
        return firstTaxRateIsApplicable(taxBase)
                ? deductionForFirstTaxThreshold(taxBase)
                : deductionForSecondTaxThreshold(taxBase);
    }

    private BigDecimal deductionForFirstTaxThreshold(BigDecimal taxBase) {
        return is(taxBase).notGreaterThan(valueOf(13000))
                ? valueOf(1360).subtract(valueOf(834.88).multiply(taxBase.subtract(firstTaxThreshold)).divide(valueOf(5000), HALF_UP))
                : valueOf(525.12);
    }

    private BigDecimal deductionForSecondTaxThreshold(BigDecimal taxBase) {
        return is(taxBase).notGreaterThan(valueOf(127000))
                ? valueOf(525.12).subtract(valueOf(525.12).multiply (taxBase.subtract(secondTaxThreshold)).divide(valueOf(41472), HALF_UP))
                : ZERO;
    }

    private BigDecimal taxWithSolidarityTax(BigDecimal taxBase) {
        return secondTaxThreshold.multiply(firstTaxRate)
                .add(taxBase.subtract(secondTaxThreshold).multiply(secondTaxRate))
                .add(taxBase.subtract(solidarityTaxThreshold).multiply(solidarityTaxRate));
    }
}

class BigDecimalComparison {
    static BigDecimalComparison is(BigDecimal value) {
        return new BigDecimalComparison(value);
    }

    private final BigDecimal referenceValue;
    private BigDecimal firstBetweenOperand;

    BigDecimalComparison(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    boolean notGreaterThan(BigDecimal valueToCompare) {
        return this.referenceValue.compareTo(valueToCompare) < 1;
    }

    BigDecimalComparison between(BigDecimal value) {
        firstBetweenOperand = value;
        return this;
    }

    boolean andInclusive(BigDecimal secondBetweenOperand) {
        return this.referenceValue.compareTo(firstBetweenOperand) > 0
                && this.referenceValue.compareTo(secondBetweenOperand) < 1;
    }
}