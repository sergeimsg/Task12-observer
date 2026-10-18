package com.task.warehouse;

import com.task.entity.ArrayStatistics;
import com.task.entity.IntegerArray;
import com.task.observer.ArrayObserver;
import com.task.service.ArrayService;


import java.util.Optional;

public class WarehouseObserver implements ArrayObserver {

    private Warehouse warehouse;
    private ArrayService arrayService;

    public WarehouseObserver(Warehouse warehouse, ArrayService arrayService) {
        this.warehouse = warehouse;
        this.arrayService = arrayService;
    }

    @Override
    public void update(IntegerArray array) {
        Optional<ArrayStatistics> arrayStatisticsOptional = arrayService.calculateStatistics(array);
        int id = array.getId();
        if (arrayStatisticsOptional.isPresent()){
            ArrayStatistics arrayStatistics = arrayStatisticsOptional.get();
            warehouse.put(id,arrayStatistics);
        }else {
            warehouse.remove(id);
        }

    }
}
