package com.inqoo.quality.clean.example.good;

import java.math.BigDecimal;

import static com.inqoo.quality.clean.example.good.BigDecimalComparison.is;
import static java.math.BigDecimal.*;
import static java.math.RoundingMode.HALF_UP;

public class PITCalculator {

    // TODO: 11.10.2020 extract those constants to other class or inject them - this will decouple often changing values from no-changing tax calculation procedure
    private final BigDecimal firstTaxThreshold = valueOf(8000);
    private final BigDecimal secondTaxThreshold = valueOf(85528);
    private final BigDecimal solidarityTaxThreshold = valueOf(1000000);

    private final BigDecimal firstTaxRate = new BigDecimal("0.17");
    private final BigDecimal secondTaxRate = new BigDecimal("0.32");
    private final BigDecimal solidarityTaxRate = new BigDecimal("0.04");


    public BigDecimal calculate(BigDecimal taxBase) {
        BigDecimal calculatedTax;
        if (taxIsNotApplicableFor(taxBase)) {
            calculatedTax = ZERO;
        } else if (firstTaxRateIsApplicableFor(taxBase)) {
            calculatedTax = calculateUsingFirstTaxThresholdFor(taxBase);
        } else if (secondTaxRateIsApplicableFor(taxBase)) {
            calculatedTax = calculateUsingSecondTaxThresholdFor(taxBase);
        } else {
            calculatedTax = calculateWithSolidarityTaxFor(taxBase);
        }
        return rounded(calculatedTax);
    }

    private boolean taxIsNotApplicableFor(BigDecimal taxBase) {
        return is(taxBase).notGreaterThan(firstTaxThreshold);
    }

    private boolean firstTaxRateIsApplicableFor(BigDecimal taxBase) {
        return is(taxBase).between(firstTaxThreshold).andInclusive(secondTaxThreshold);
    }

    private BigDecimal calculateUsingFirstTaxThresholdFor(BigDecimal taxBase) {
        return taxBase.multiply(firstTaxRate)
                .subtract(taxDeduction(taxBase));
    }

    private boolean secondTaxRateIsApplicableFor(BigDecimal taxBase) {
        return is(taxBase).between(secondTaxThreshold).andInclusive(solidarityTaxThreshold);
    }

    private BigDecimal calculateUsingSecondTaxThresholdFor(BigDecimal taxBase) {
        return secondTaxThreshold.multiply(firstTaxRate)
                .add((taxBase.subtract(secondTaxThreshold).multiply(secondTaxRate)))
                .subtract(taxDeduction(taxBase));
    }

    // TODO: 11.10.2020 calculating of tax deduction should be extracted to separated class
    // tax deduction calculated according to https://www.e-pity.pl/kwota-wolna-od-podatku-pit-kalkulator/
    private BigDecimal taxDeduction(BigDecimal taxBase) {
        return firstTaxRateIsApplicableFor(taxBase)
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
                ? valueOf(525.12).subtract(valueOf(525.12).multiply(taxBase.subtract(secondTaxThreshold)).divide(valueOf(41472), HALF_UP))
                : ZERO;
    }

    private BigDecimal calculateWithSolidarityTaxFor(BigDecimal taxBase) {
        return secondTaxThreshold.multiply(firstTaxRate)
                .add(taxBase.subtract(secondTaxThreshold).multiply(secondTaxRate))
                .add(taxBase.subtract(solidarityTaxThreshold).multiply(solidarityTaxRate));
    }

    private BigDecimal rounded(BigDecimal calculatedTax) {
        return calculatedTax.setScale(0, HALF_UP);
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

    // TODO: 11.10.2020 only this method uses firstBetweenOperand - maybe more specialized class for between for sake of better cohesion?
    BigDecimalComparison between(BigDecimal value) {
        firstBetweenOperand = value;
        return this;
    }

    boolean andInclusive(BigDecimal secondBetweenOperand) {
        return this.referenceValue.compareTo(firstBetweenOperand) > 0
                && this.referenceValue.compareTo(secondBetweenOperand) < 1;
    }
}