package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.FileManager;
import utilities.Logs;

public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Logs.debug("on suite starts");
        FileManager.deleteTestEvidence();
        FileManager.redirectStdErr();
    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.debug("on suite finish");
    }
}
