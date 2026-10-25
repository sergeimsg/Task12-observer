package edu.melnik.task12.warehouse;

import edu.melnik.task12.entity.ArrayStatistics;
import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.observer.ArrayObserver;
import edu.melnik.task12.repository.impl.ArrayRepositoryImpl;
import edu.melnik.task12.service.ArrayService;
import edu.melnik.task12.service.impl.ArrayServiceImpl;
import edu.melnik.task12.util.ArrayEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WarehouseObserver implements ArrayObserver {

    private static final Logger logger = LogManager.getLogger();
    private Warehouse warehouse;
    private ArrayService arrayService;
    // private ArrayRepositoryImpl arrayRepository = ArrayRepositoryImpl.getInstance();
    List<IntegerArray> arrayList = new ArrayList<>();

    public WarehouseObserver() {
        this.warehouse = Warehouse.getInstance();
        this.arrayService = new ArrayServiceImpl();

    }

    @Override
    public void addObservalable(IntegerArray array) {
        arrayList.add(array);
    }

    @Override
    public void removeObservalable(IntegerArray array) {
        arrayList.remove(array);
    }

    @Override
    public void update(ArrayEvent event) {
        if (event != null && event.getSource() != null) {
            Iterator<IntegerArray> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                IntegerArray array = event.getSource();
                Optional<ArrayStatistics> arrayStatisticsOptional = arrayService.calculateStatistics(array);
                int id = array.getId();
                IntegerArray arrayCheck = iterator.next();
                int idFromArrayList = arrayCheck.getId();
                Optional<ArrayStatistics> arrayStatisticsOptional1 = arrayService.calculateStatistics(arrayCheck);
                if(id == idFromArrayList){
                //logger.info("/////////// WAREHOUSE UPDATE /////// {}", this.warehouse.find(id).toString());
                if (arrayStatisticsOptional.isPresent()) {
                    ArrayStatistics arrayStatistics = arrayStatisticsOptional.get();
                    warehouse.put(id, arrayStatistics);
                } else {
                    warehouse.remove(id);
                } } else { if(arrayStatisticsOptional1.isPresent()) {warehouse.put(idFromArrayList, arrayStatisticsOptional1.get());} else {
                    warehouse.remove(idFromArrayList);
                }

                }
                //  logger.info("/////////// WAREHOUSE AFTER ALL /////// {}", arrayStatisticsOptional);
            }
        } else {
            logger.warn("Empty event");
        }
    }


}
