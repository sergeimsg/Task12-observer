package edu.melnik.task12.comparator;

import edu.melnik.task12.entity.IntegerArray;

import java.util.Comparator;

public class LengthComparator implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray o1, IntegerArray o2) {
        int first = o1.getLength();
        int second = o2.getLength();
        return Integer.compare(first,second);
    }
}
