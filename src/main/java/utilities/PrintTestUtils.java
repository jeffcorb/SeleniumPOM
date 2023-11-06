package utilities;

public class PrintTestUtils {
    public static void printSuccess(String className, String testName) {
        final var status = "PASSED";
        System.out.printf("\t %s.%s ... \u001B[32m%s\u001B[0m%n", className, testName, status);
    }

    public static void printFailed(String className, String testName) {
        final var status = "FAILED";
        System.out.printf("\t %s.%s ... \u001B[31m%s\u001B[0m%n", className, testName, status);
    }

    public static void printSkipped(String className, String testName) {
        final var status = "SKIPPED";
        System.out.printf("\t %s.%s ... \u001B[33m%s\u001B[0m%n", className, testName, status);
    }
}