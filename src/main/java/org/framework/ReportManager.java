package org.framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Reporter;

public class ReportManager {
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss ZZZZ";

    /**
     * This method is used to print log message
     * @param logText desired log message
     */
    public static void log(String logText) {
        createLogEntry(logText);
    }

    /**
     * This method is used to print log message in specific format (2023-07-27 09:12:32 +0200 [ReportManager] logText)
     * @param logText desired log message
     */
    private static void createLogEntry(String logText) {
        String timestamp = (new SimpleDateFormat(TIMESTAMP_FORMAT)).format(new Date(System.currentTimeMillis()));
        String log = timestamp + " [ReportManager] " + logText.trim();
        Reporter.log(log, true);
    }
}
