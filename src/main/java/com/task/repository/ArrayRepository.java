package com.task.repository;

import com.task.entity.ArrayStatistics;
import com.task.entity.IntegerArray;

import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {
    boolean add(IntegerArray array);
    boolean remove(IntegerArray array);
    void clear();
    List<IntegerArray> findAll();
    List<IntegerArray> query(ArrayStatistics arrayStatistics);
    List<IntegerArray> sort(Comparator<IntegerArray> comparator);


}
