package edu.melnik.task12.entity;

import edu.melnik.task12.exception.ArrayUserException;
import edu.melnik.task12.observer.ArrayObserver;
import edu.melnik.task12.observer.ArrayObservable;
import edu.melnik.task12.util.ArrayEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Entity class that wraps a primitive int array.
 * The internal array is defensively copied on construction and access.
 */
public class IntegerArray extends AbstractArray implements ArrayObservable {

    Logger logger = LogManager.getLogger();
    private int id;
    private final int[] data;
    //private final ArrayList<ArrayObserver> observers = new ArrayList<>();
    private ArrayObserver observers;


    public IntegerArray(int[] value) throws ArrayUserException {
        if (value == null) {
            throw new ArrayUserException("Array value cannot be null");
        } else if (value.length == 0) {
            data = value;
        } else {
            //one variant
            //this.data = Arrays.copyOf(value, value.length);
            //another variant
            this.data = value.clone();
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

    public void setValue(int index, int value) throws ArrayUserException {
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
        if (ob != null) {
            //observers.add(ob);
            this.observers = ob;
            logger.info("Add observer {} ---------------------- + observers {} ", ob.toString(), observers);
            observers.addObservalable(this);
        }
    }

    @Override
    public void detach(ArrayObserver ob) {
        //observers.remove(ob);
        observers = null;
        ob.removeObservalable(this);
    }

    @Override
    public void notifyObservers() {
        ArrayEvent arrayEvent = new ArrayEvent(this);
        // for (ArrayObserver observer : observers) {
        logger.info("------------------ /--------/ BEFORE NOTIFY ONSERVERS         observer {}", data);
        if (observers != null) {
            observers.update(arrayEvent);
        }
        logger.info("------------------ AFTER NOTIFY ONSERVERS         observer {}", data);
        //}
        //logger.info("------------------ NOTIFY   ///      observer {}", observers.stream().toArray());
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
