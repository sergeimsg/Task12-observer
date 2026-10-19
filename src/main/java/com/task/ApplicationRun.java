package com.task;


import com.task.comparator.FirstDigitComparatot;
import com.task.comparator.IdComparator;
import com.task.comparator.LengthComparator;
import com.task.entity.IntegerArray;
import com.task.exception.ArrayUserException;
import com.task.factory.ArrayFactory;
import com.task.factory.impl.IntegerArrayFactory;
import com.task.parser.ArrayParser;
import com.task.parser.impl.ArrayParserImpl;
import com.task.reader.DataReader;
import com.task.reader.impl.DataReaderImpl;
import com.task.repository.ArrayRepository;
import com.task.repository.impl.ArrayRepositoryImpl;
import com.task.service.ArrayService;
import com.task.service.impl.ArrayServiceImpl;

import com.task.validator.ArrayValidator;
import com.task.validator.impl.ArrayValidatorImpl;
import com.task.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Application entry point demonstrating Factory Method and Builder patterns,
 * file reading, validation, parsing, and array service operations.
 */
public class ApplicationRun {

    private static final Logger logger = LogManager.getLogger();

    private final DataReader reader;
    private final ArrayValidator validator;
    private final ArrayParser parser;
    private final ArrayFactory factory;
    private final ArrayService service;
    private final Warehouse warehouse;
    private final ArrayRepository arrayRepository;


    public ApplicationRun() {
        this.reader = new DataReaderImpl();
        this.validator = new ArrayValidatorImpl();
        this.parser = new ArrayParserImpl();
        this.factory = new IntegerArrayFactory();
        this.service = new ArrayServiceImpl();
        this.warehouse = Warehouse.getInstance();
        this.arrayRepository = ArrayRepositoryImpl.getInstance();

    }

    public static void main(String[] args) throws ArrayUserException {
        ApplicationRun main = new ApplicationRun();
        main.run();

    }

    public void run() throws ArrayUserException {


        String filePath = "src/main/resources/data/array_data.txt";
        logger.info("Reading arrays from: {}", filePath);

        List<String> lines = reader.readLines(filePath);

        List<IntegerArray> arrays = new ArrayList<>();
        for (String line : lines) {
            if (validator.isLineValid(line)) {
                int[] data = parser.parse(line);
                IntegerArray array = factory.createArray(data);
                arrayRepository.add(array);
                logger.info("Array from line ---------id --{}  array  {}",array.getId(),  array.toString());
                arrays.add(array);
                logger.info("Created array from valid line: '{}'", line);
            } else {
                logger.warn("Skipped invalid line: '{}'", line);
            }
        }
        logger.info("Total arrays created: {}", arrays.size());
        logger.debug("We have array: {}",arrays.toString());
        showRepository();
        Comparator<IntegerArray> comparator = new LengthComparator();

        int firstDigitCompare = comparator.compare(arrayRepository.findAll().get(0), arrayRepository.findAll().get(3));
        logger.info("---------------------------");
        arrayRepository.findAll().get(0).setValue(0,10);
        logger.info(arrayRepository.findAll().get(0).getData()[0]);
        //arrayRepository.sort(ompare(arrayRepository.findAll().get(1), arrayRepository.findAll().get(3)));
        arrayRepository.sort(comparator);
        logger.info("First digit compare{}", firstDigitCompare);
        showRepository();


        /**

        for (int i = 0; i < arrays.size(); i++) {
            IntegerArray array = arrays.get(i);
            logger.info("Array #{}: {}", i, array);

            if (array.getLength() > 0) {
                Optional<Integer> min = service.findMin(array);
                Optional<Integer> max = service.findMax(array);
                Optional<Integer> sum = service.calculateSum(array);
                Optional<Double> avg = service.calculateAverage(array);
                arrayStatistics = new ArrayStatistics(min.get(), max.get(), sum.get(), avg.get(), array.getLength());
                logger.info("ArrayStatistics ******************** {}", arrayStatistics.toString());
                logger.info("Array={} Min={}, Max={}, Sum={}, Average={}", array.toString(),
                        min.orElse(null), max.orElse(null),
                        sum.orElse(null), avg.orElse(null));

                IntegerArray bubbleSorted = service.bubbleSort(array);
                IntegerArray quickSorted = service.quickSort(array);
                logger.info("  Bubble sorted: {}", bubbleSorted);
                logger.info("  Quick sorted:  {}", quickSorted);
            } else {
                logger.info("  Empty array — no operations to perform");
            }
        }
        **/
    }

    public void showRepository(){
        List<IntegerArray> arrays = arrayRepository.findAll();
        for (IntegerArray array : arrays){
            int id = array.getId();
            logger.info("REpository {}", arrayRepository.findAll());
            logger.info("Warehouse id {} warehouse  {}",id,  warehouse.find(id).toString());
        }
    }

}
