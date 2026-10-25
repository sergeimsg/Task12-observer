package edu.melnik.task12.warehouse;

import edu.melnik.task12.entity.ArrayStatistics;

import java.util.HashMap;
import java.util.Optional;

public class Warehouse {

    private static Warehouse instance;

    private final HashMap<Integer, ArrayStatistics> arrayStatisticsWarehouse;

    private Warehouse() {
        this.arrayStatisticsWarehouse = new HashMap();
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
