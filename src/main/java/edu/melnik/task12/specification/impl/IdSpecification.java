package edu.melnik.task12.specification.impl;

import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.specification.ArraySpecification;

public class IdSpecification implements ArraySpecification {

    private final int expectedId;

    public IdSpecification(int expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public boolean specify(IntegerArray array) {
        if (array == null) {
            return false;
        } else {
            return array.getId() == expectedId;
        }


    }
}
