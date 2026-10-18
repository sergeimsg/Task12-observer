package com.task.repository.impl;

import com.task.entity.ArrayStatistics;
import com.task.entity.IntegerArray;
import com.task.observer.ArrayObserver;
import com.task.repository.ArrayRepository;
import com.task.service.impl.ArrayServiceImpl;
import com.task.warehouse.Warehouse;
import com.task.warehouse.WarehouseObserver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayRepositoryImpl implements ArrayRepository {

    private static ArrayRepositoryImpl instance;
    private Warehouse warehouse;
    private ArrayObserver arrayObserver;

    private final List<IntegerArray> arrays = new ArrayList<>();

    private ArrayRepositoryImpl() {
        this.warehouse = Warehouse.getInstance();
        this.arrayObserver = new WarehouseObserver(warehouse, new ArrayServiceImpl());
    }

    public static  ArrayRepositoryImpl getInstance(){
        if (instance==null){
            instance=new ArrayRepositoryImpl();
        }
        return instance;
    }


    @Override
    public boolean add(IntegerArray array) {
        boolean isNew = !arrays.contains(array);
        if (isNew) {
            arrays.add(array);
            array.attach(arrayObserver);
            arrayObserver.update(array);
        }
        return isNew;
    }

    @Override
    public boolean remove(IntegerArray array) {
        boolean isRemoved = arrays.remove(array);
        if (isRemoved) {
            array.detach(arrayObserver);
            int id = array.getId();
            warehouse.remove(id);
        }
        return isRemoved;
    }

    @Override
    public void clear() {

        for (IntegerArray array : arrays){
            array.detach(arrayObserver);
            int id = array.getId();
            warehouse.remove(id);
        }
    }

    @Override
    public List<IntegerArray> findAll() {
        return new ArrayList<>(arrays);
    }

    @Override
    public List<IntegerArray> query(ArrayStatistics arrayStatistics) {
        return List.of();
    }

    @Override
    public List<IntegerArray> sort(Comparator<IntegerArray> comparator) {
        List<IntegerArray> sorted = new ArrayList<>(arrays);
        sorted.sort(comparator);
        return sorted;
    }
}
