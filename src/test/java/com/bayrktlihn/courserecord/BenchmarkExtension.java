package com.bayrktlihn.courserecord;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

public class BenchmarkExtension implements BeforeAllCallback, AfterAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME = "StartTime";

    private Logger logger = Logger.getLogger(BenchmarkExtension.class.getName());

    @Override
    public void afterAll(ExtensionContext context) throws Exception {

        final LocalDateTime startTime = getStoreForContainer(context).remove(START_TIME, LocalDateTime.class);
        final long runTime = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
        logger.info(String.format("Run time for test container <%s>: %s ms", context.getRequiredTestClass().getName(), runTime));
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        getStoreForContainer(context).put(START_TIME, LocalDateTime.now());
    }

    private ExtensionContext.Store getStoreForContainer(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestClass()));
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        final LocalDateTime startTime = getStoreForMethod(context).remove(START_TIME, LocalDateTime.class);
        final long runTime = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
        logger.info(String.format("Run time for test method <%s>: %s ms", context.getRequiredTestMethod().getName(), runTime));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStoreForMethod(context).put(START_TIME, LocalDateTime.now());
    }

    private ExtensionContext.Store getStoreForMethod(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }


}
