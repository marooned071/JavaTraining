package me.training.whiteboard;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AverageExecutionTimeLoggerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback, BeforeAllCallback {

    private static final Logger logger = Logger.getLogger(AverageExecutionTimeLoggerExtension.class.getName());

    private static final String START_TIME = "start time";
    private static final String TIMING_LIST = "timing list";
    private static final String COUNT = "count";

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        Store store = getStore(context);
        store.put(START_TIME, System.currentTimeMillis());
        store.get(COUNT, AtomicInteger.class).getAndIncrement();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Store store = getStore(context);
        long startTime = store.remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;
        List<Long> arrayList = (ArrayList<Long>) store.get(TIMING_LIST, ArrayList.class);
        arrayList.add(duration);
    }


    private Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass()));
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        getStore(context).put(TIMING_LIST, new ArrayList<Long>());
        getStore(context).put(COUNT, new AtomicInteger());
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Store store = getStore(context);
        List<Long> arrayList = (ArrayList<Long>) store.remove(TIMING_LIST, ArrayList.class);
        Double average = arrayList.stream().mapToLong(l -> l).average().orElseThrow();
        String className = context.getRequiredTestClass().getName();
        int count = store.remove(COUNT, AtomicInteger.class).get();
        logger.info(String.format("Average time of %d runs: [%s]:[%s] %s ms.", count, className, context.getDisplayName(), average));
    }


}