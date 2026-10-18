package com.task.comparator;

import com.task.entity.IntegerArray;

import java.util.Comparator;

public class FirstDigitComparatot implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray o1, IntegerArray o2) {
        int digit1 = 0;
        int digit2 = 0;
        if (o1.getLength() == 0) {
            digit1=Integer.MIN_VALUE;
        } else if (o2.getLength()==0) {
            digit2=Integer.MIN_VALUE;
        } else {
            digit1 = o1.getData()[0];
            digit2 = o2.getData()[0];
        }
        return Integer.compare(digit1, digit2);
    }
}
