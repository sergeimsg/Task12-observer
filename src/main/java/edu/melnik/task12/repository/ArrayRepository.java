package edu.melnik.task12.repository;

import edu.melnik.task12.entity.ArrayStatistics;
import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.specification.ArraySpecification;
import edu.melnik.task12.specification.impl.IdSpecification;

import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {
    boolean add(IntegerArray array);
    boolean remove(IntegerArray array);
    void clear();
    List<IntegerArray> findAll();
    List<IntegerArray> query(ArraySpecification arraySpecification);
    List<IntegerArray> sort(Comparator<IntegerArray> comparator);


}
