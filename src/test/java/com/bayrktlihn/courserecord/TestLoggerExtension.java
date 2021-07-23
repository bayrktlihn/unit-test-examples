package com.bayrktlihn.courserecord;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Logger;

public class TestLoggerExtension implements BeforeAllCallback, AfterAllCallback {

    private static Logger log = Logger.getLogger(TestLoggerExtension.class.getName());


    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        log.info(String.format("Test is started...%s", extensionContext.getDisplayName()));
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {

        log.info(String.format("Test is ended...%s", extensionContext.getDisplayName()));
    }
}
