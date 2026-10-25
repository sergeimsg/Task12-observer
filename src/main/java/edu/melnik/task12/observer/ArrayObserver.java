package edu.melnik.task12.observer;

import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.util.ArrayEvent;

public interface ArrayObserver {
     void addObservalable(IntegerArray array);
     void removeObservalable(IntegerArray array);
     void update(ArrayEvent array);
}
