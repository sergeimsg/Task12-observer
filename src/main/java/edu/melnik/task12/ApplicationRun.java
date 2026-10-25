package edu.melnik.task12;


import edu.melnik.task12.comparator.FirstDigitComparatot;
import edu.melnik.task12.comparator.IdComparator;
import edu.melnik.task12.comparator.LengthComparator;
import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.exception.ArrayUserException;
import edu.melnik.task12.factory.ArrayFactory;
import edu.melnik.task12.factory.impl.IntegerArrayFactory;
import edu.melnik.task12.parser.ArrayParser;
import edu.melnik.task12.parser.impl.ArrayParserImpl;
import edu.melnik.task12.reader.DataReader;
import edu.melnik.task12.reader.impl.DataReaderImpl;
import edu.melnik.task12.repository.ArrayRepository;
import edu.melnik.task12.repository.impl.ArrayRepositoryImpl;
import edu.melnik.task12.service.ArrayService;
import edu.melnik.task12.service.impl.ArrayServiceImpl;

import edu.melnik.task12.specification.impl.IdSpecification;
import edu.melnik.task12.validator.ArrayValidator;
import edu.melnik.task12.validator.impl.ArrayValidatorImpl;
import edu.melnik.task12.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    //    logger.info("Reading arrays from: {}", filePath);

        List<String> lines = reader.readLines(filePath);

        List<IntegerArray> arrays = new ArrayList<>();
        for (String line : lines) {
            if (validator.isLineValid(line)) {
                int[] data = parser.parse(line);
                IntegerArray array = factory.createArray(data);
                arrayRepository.add(array);
           //     logger.info("Array from line ---------id --{}  array  {}",array.getId(),  array.toString());
                arrays.add(array);
           //     logger.info("Created array from valid line: '{}'", line);
            } else {
                logger.warn("Skipped invalid line: '{}'", line);
            }
        }
        //logger.info("Total arrays created: {}", arrays.size());
        logger.debug("We have array: {}",arrays.toString());
        showRepository();
        Comparator<IntegerArray> firstDigitComparatot = new FirstDigitComparatot();
        Comparator<IntegerArray> idComparator = new IdComparator();
       // idComparator.compare(arrayRepository.findAll().get(1), arrayRepository.findAll().get(0));
        Comparator<IntegerArray> lengthComparator = new LengthComparator();
     //   lengthComparator.compare(arrayRepository.findAll().get(0), arrayRepository.findAll().get(1));
        logger.info("---------------------------");
        arrayRepository.sort(firstDigitComparatot);
        arrayRepository.sort(lengthComparator);
        arrayRepository.sort(idComparator);
        logger.info("+++++++++++++++SETTTTTTTTTTTTTTTT  ++++++++++++++++++++++++++++++++");
        arrayRepository.findAll().get(0).setValue(0,600);
        logger.info("+++++++++++++++SETTTTT            AFTER           TTTTTTTTTTT  ++++++++++++++++++++++++++++++++");


        showRepository();
        logger.info("+++++++++++++++REMOVE arrays.get(8) ++++++++++++++++++++++++++++++++");
        arrayRepository.remove(arrays.get(8));
        showRepository();
        IdSpecification idSpecification = new IdSpecification(5);
        arrayRepository.query(idSpecification);
      //  logger.info("+++++++++++++++CLEAR++++++++++++++++++++++++++++++++");
//        arrayRepository.clear();
//        showRepository();

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
        logger.info("Repository {}", arrayRepository.findAll());
        for (IntegerArray array : arrays){
            int id = array.getId();
            logger.info("Warehouse id = {} warehouse -  {}",id, warehouse.find(id).toString());
        }
    }

}
