package com.inqoo.quality.naming;

public class Executor {
    public int perform(int[] data) {
        int end = data.length;
        int x = 0;
        for (int i = 0; i < end; i++) {
            if (x < data[i]) x = data[i];
        }

        return x;
    }
}

