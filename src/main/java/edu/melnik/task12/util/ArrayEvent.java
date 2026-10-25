package edu.melnik.task12.util;

import edu.melnik.task12.entity.IntegerArray;

public class ArrayEvent {

    private final IntegerArray source;

    public ArrayEvent(IntegerArray source) {
        this.source = source;
    }

    public IntegerArray getSource(){
        return source;
    }
}
