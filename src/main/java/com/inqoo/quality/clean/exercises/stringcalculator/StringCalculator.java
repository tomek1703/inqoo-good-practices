package com.inqoo.quality.clean.exercises.stringcalculator;

public class StringCalculator {
    public String add(String params) {
        if (params.isEmpty()) {
            return "0";
        } else {
            return paramsSum(params);
        }
    }

    private String paramsSum(String params) {
        String[] splittedParams = params.split("\n");
        int sum = 0;
        String delimeter = ",";


        for (int i = 1; i < splittedParams.length; i++) {
            sum += rowSum(splittedParams[i], delimeter);
        }
        return String.valueOf(sum);
    }

    private int rowSum(String splittedParam, String delimeter) {
        int sum = 0;
        String[] split = splittedParam.split(delimeter);
        for (int i = 0; i < split.length; i++) {
            sum += Integer.parseInt(split[i]);
        }
        return sum;
    }
}

/*
1\n2,3 ->
1\n2
3

[
[1,2]
[3]
]



 */
