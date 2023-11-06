package utilities;

import base.BaseTest;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots";
    private final static String allurePath = "target/allure-results";
    private final static String xmlPath = "src/test/resources/xml";

    public static void redirectStdErr() {
        Logs.debug("Redirecting Stderr");
        final var file = new File("src/test/resources/logs/stderr.log");
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final var ps = new PrintStream(fos);
        System.setErr(ps);
    }

    public static void getScreenshot(String screenShotName) {
        Logs.debug("Taking Screenshot");
        final var screenshotFile = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/%s.png", screenshotPath, screenShotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("ioException: %s ", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSourceXML(String fileName) {
        Logs.debug("Taking Page Source");
        final var path = String.format("%s/view-hierarchy-%s.html", xmlPath, fileName);

        try {
            Logs.debug("Creating html file: %s", path);
            final var file = new File(path);
            Logs.debug("Creating file parents");
            if (file.getParentFile().mkdirs()) {
                Logs.debug("Writing to html file");
                final var fileWriter = new FileWriter(file);
                fileWriter.write(BaseTest.getDriver().getPageSource());
                Logs.debug("Closing FileWriter");
                fileWriter.close();
            }

        } catch (IOException ioException) {
            Logs.error("Failed to create/write html: %s", ioException.getLocalizedMessage());
        }
    }

    @Attachment
    public static byte[] getAllureScreenshot() {
        Logs.debug("Taking Allure Screenshot");
        return ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void deleteTestEvidence() {
        try {
            Logs.debug("Deleting Screenshot Folder");
            FileUtils.deleteDirectory(new File(screenshotPath));

            Logs.debug("Deleting xml Folder");
            FileUtils.deleteDirectory(new File(xmlPath));

            Logs.debug("Deleting Allure results folder");
            FileUtils.deleteDirectory(new File(allurePath));
        } catch (IOException ioException) {
            Logs.error("ioException: %s ", ioException.getLocalizedMessage());
        }
    }
}