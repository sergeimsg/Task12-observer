package com.task.comparator;

import com.task.entity.IntegerArray;

import java.util.Comparator;

public class IdComparator implements Comparator<IntegerArray> {

    @Override
    public int compare(IntegerArray o1, IntegerArray o2) {
        int id1 = o1.getId();
        int id2= o2.getId();
        return Integer.compare(id1, id2);
    }
}
