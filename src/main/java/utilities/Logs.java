package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Formatter;

public class Logs {
    private final static Logger log = LogManager.getLogger("AUTOMATION");

    public static void debug(String message) {
        log.debug(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void debug(String format, Object... args) {
        log.debug(new Formatter().format(format, args).toString());
    }

    public static void info(String format, Object... args) {
        log.info(new Formatter().format(format, args).toString());
    }

    public static void error(String format, Object... args) {
        log.error(new Formatter().format(format, args).toString());
    }
}
