package com.task.warehouse;

import com.task.entity.ArrayStatistics;

import java.util.HashMap;
import java.util.Optional;

public class Warehouse {

    private static Warehouse instance;

    private final HashMap<Integer, ArrayStatistics> arrayStatisticsWarehouse = new HashMap();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(int id, ArrayStatistics arrayStatistics){
            arrayStatisticsWarehouse.put(id, arrayStatistics);
    }

    public void remove(int id){
            arrayStatisticsWarehouse.remove(id);
    }

    public Optional<ArrayStatistics> find(int id){
        ArrayStatistics arrayStatistics = arrayStatisticsWarehouse.get(id);
        return Optional.ofNullable(arrayStatistics);
    }

}
