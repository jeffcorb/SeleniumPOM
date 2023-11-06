package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import utilities.FileManager;
import utilities.Logs;

public class AllureListeners implements TestLifecycleListener {
    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("Before test stops Allure Listener  ");
        final var resultType = result.getStatus().name();
        if (resultType.equalsIgnoreCase("FAILED") || resultType.equalsIgnoreCase("BROKEN")) {
            Logs.debug("Test failing taking Allure Screenshot");
            FileManager.getAllureScreenshot();
        }
    }
}