package com.inqoo.quality.clean.exercises.stringcalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    private final StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void returnsZeroWhenParamIsEmpty () {
        // when
        String result = stringCalculator.add("");

        // then
        assertEquals("0", result);
    }

    @Test
    public void returnsInputWhenSingleNumber () {
        // when
        String result = stringCalculator.add("1");

        // then
        assertEquals("1", result);
    }

    @Test
    public void returnsSumOfTwoNumbers () {
        // when
        String result = stringCalculator.add("1,2");

        // then
        assertEquals("3", result);
    }
    @Test
    public void returnsSumOfThreeNumbers () {
        // when
        String result = stringCalculator.add("1,2,3");

        // then
        assertEquals("6", result);
    }
    @Test
    public void returnsSumOfThreeNumbersWithEndLineAsSeparator () {
        // when
        String result = stringCalculator.add("1\n2,3");

        // then
        assertEquals("6", result);
    }

    @Test
    public void returnsSumWithDelimeter () {
        // when
        String result = stringCalculator.add("//;\n1;2;3");

        // then
        assertEquals("6", result);
    }
}
