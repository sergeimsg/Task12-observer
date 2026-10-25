package edu.melnik.task12.repository.impl;

import edu.melnik.task12.entity.ArrayStatistics;
import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.service.impl.ArrayServiceImpl;
import edu.melnik.task12.observer.ArrayObserver;
import edu.melnik.task12.repository.ArrayRepository;
import edu.melnik.task12.specification.ArraySpecification;
import edu.melnik.task12.specification.impl.IdSpecification;
import edu.melnik.task12.warehouse.Warehouse;
import edu.melnik.task12.warehouse.WarehouseObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ArrayRepositoryImpl implements ArrayRepository {

    private static final Logger logger = LogManager.getLogger();
    private static ArrayRepositoryImpl instance;

    private ArrayObserver arrayObserver;

    private final List<IntegerArray> arrays= new ArrayList<>();

    private ArrayRepositoryImpl() {
        Warehouse warehouse = Warehouse.getInstance();
        this.arrayObserver = new WarehouseObserver();
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }


    @Override
    public boolean add(IntegerArray array) {
        boolean isNew = !arrays.contains(array);
        if (isNew) {
            arrays.add(array);
            array.attach(arrayObserver);
            array.notifyObservers();
         //   arrayObserver.update(array);

        }
        return isNew;
    }

    @Override
    public boolean remove(IntegerArray array) {
        logger.info("------------------ BEFORE REMOVAL observer {}", arrays);
        boolean isRemoved = arrays.remove(array);
        if (isRemoved) {
                array.detach(arrayObserver);
                array.notifyObservers();

        }
        return isRemoved;
    }

    @Override
    public void clear() {

        for (IntegerArray array : arrays) {
            array.detach(arrayObserver);
            array.notifyObservers();
        }
        arrays.clear();

//        logger.info("+++++++++++++++CLEARed++++++++++++++++++++++++++++++++");
//        logger.info("After cleared ******** {}", arrays);
//        logger.info("After cleared ******** {}", arrayObserver);
    }

    @Override
    public List<IntegerArray> findAll() {

        return new ArrayList<>(arrays);
    }


    @Override
    public List<IntegerArray> query(ArraySpecification arraySpecification) {
            List<IntegerArray> result = new ArrayList<>();
            this.arrays.stream().filter(arraySpecification::specify).forEach(result::add);
            logger.info("Result {}", result);
        return result;
    }

    @Override
    public List<IntegerArray> sort(Comparator<IntegerArray> comparator) {
        List<IntegerArray> sorted = new ArrayList<>(arrays);
        //logger.info("Before sorted ******** {}", sorted);
        sorted.sort(comparator);
        //logger.info("After sorted ******** {}", sorted);
        return sorted;
    }
}
