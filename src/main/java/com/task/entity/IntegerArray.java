package com.task.entity;

import com.task.exception.ArrayUserException;
import com.task.observer.ArrayObserver;
import com.task.observer.ArrayObservable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Entity class that wraps a primitive int array.
 * The internal array is defensively copied on construction and access.
 */
public class IntegerArray extends AbstractArray implements ArrayObservable {

    private int id;
    private final int[] data;
    private final ArrayList<ArrayObserver> observers = new ArrayList<>();


    public IntegerArray(int[] value) throws ArrayUserException {
        if (value == null) {
            throw new ArrayUserException("Array value cannot be null");
        } else if (value.length == 0) {
            data = value;
        } else {
            this.data = Arrays.copyOf(value, value.length);
        }
    }

    public IntegerArray(int id, int[] value) throws ArrayUserException {
        this.id = id;
        if (value == null) {
            throw new ArrayUserException("Array value cannot be null");
        } else if (value.length == 0) {
            data = value;
        } else {
            this.data = Arrays.copyOf(value, value.length);
        }
    }

    @Override
    public int[] getData() {
        return Arrays.copyOf(data, data.length);
    }


    @Override
    public int getLength() {
        return data.length;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setValue(int index, int value) throws ArrayUserException{
        if (index < 0 || index >= data.length) {
            throw new ArrayUserException("Index out of bounds of array: " + index);
        }
            data[index] = value;
        notifyObservers();
    }

    public int getElementAt(int index) throws ArrayUserException {
        if (index < 0 || index >= data.length) {
            throw new ArrayUserException("Index out of bounds of array: " + index);
        }
        return data[index];
    }

    @Override
    public void attach(ArrayObserver ob) {
        observers.add(ob);
    }

    @Override
    public void detach(ArrayObserver ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObservers() {
        for (ArrayObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntegerArray other = (IntegerArray) obj;
        if (data.length != other.data.length) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] != other.data[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int value : data) {
            result = 31 * result + value;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IntegerArray{id=");
        sb.append(getId());
        sb.append(",");
        sb.append("data=[");
        for (int i = 0; i < data.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[i]);
        }
        sb.append("]}");
        return sb.toString();
    }


}
