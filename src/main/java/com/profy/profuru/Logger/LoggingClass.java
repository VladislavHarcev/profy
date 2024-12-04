package com.profy.profuru.Logger;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
public class LoggingClass {

    private static final Logger HTTP_REQUEST_LOGGER = LoggerFactory.getLogger("org.springframework.web.servlet.DispatcherServlet");
    private static final Logger INFO_LOGGER = LoggerFactory.getLogger("com.profuru");
    private static final Logger WARN_LOGGER = LoggerFactory.getLogger("com.profuru");
    private static final Logger ERROR_LOGGER = LoggerFactory.getLogger("com.profuru");

    public static void logHttpRequest(String message) {
        HTTP_REQUEST_LOGGER.info(message);
    }
    public static void logInfo(String message) {
        INFO_LOGGER.info(message);
    }
    public static void logWarn(String message) {
        WARN_LOGGER.warn(message);
    }
    public static void logError(String message) {
        ERROR_LOGGER.error(message);
    }
}
