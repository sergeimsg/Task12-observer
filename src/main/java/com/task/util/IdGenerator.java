package com.task.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);
    //private static final UUID uuid = UUID.randomUUID();

    private IdGenerator(){}

    public static int newIdMaker(){
        return counter.addAndGet(1);
      //  return uuid.randomUUID();
    }
}
