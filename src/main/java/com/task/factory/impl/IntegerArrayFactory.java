package com.task.factory.impl;

import com.task.entity.IntegerArray;
import com.task.exception.ArrayUserException;
import com.task.factory.ArrayFactory;
import com.task.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete factory that creates IntegerArray instances with name and array.
 */
public class IntegerArrayFactory implements ArrayFactory {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public IntegerArray createArray(int[] data) throws ArrayUserException {
        if (data == null) {
            throw new ArrayUserException("Cannot create array from null data");
        }

        return createArray(IdGenerator.newIdMaker(), data);
    }

    @Override
    public IntegerArray createArray(int id,int[] data) throws ArrayUserException {
        if (data==null){
            throw new ArrayUserException("Cannot create array from null data");
        }
        IntegerArray array = new IntegerArray(id,data);
        logger.debug("Created IntegerArray with length {}", array.getLength());
        return array;
    }
}
