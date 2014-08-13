package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 4:49 PM
 */
public class ExportQRCodeTaskTest extends TaskBase {
    public ExportQRCodeTaskTest() {
        super("test");
    }

    @Test
    @Ignore
    public void testExecute() throws Exception {
        NRCClient nrcClient = new NRCClient("localhost:8080");
        ExportQRCodeTask exportQRCodeTask = new ExportQRCodeTask(nrcClient, 3);
        exportQRCodeTask.execute();
    }

    @Test
    @Ignore
    public void testExecuteScroll() throws Exception {
        Thread.sleep(2000);
        clickTarget(Targets.groupMemberSummary, LONGER_WAIT_TIMEOUT);
        ScreenRegion home = mainScreenRegion.find(Targets.home);
        ScreenLocation bottom = home.getRelativeScreenLocation(20, -60);
        ScreenLocation top = Relative.to(bottom).above(500).getScreenLocation();
        mouse.drag(bottom);
        mouse.drop(top);
        mouse.wheel(1, 40);
        System.out.println("Reach the bottom.");
        Thread.sleep(1000);
        mouse.drag(top);
        mouse.drop(Relative.to(top).below(250).getScreenLocation());
        Thread.sleep(1000);
        clickTarget(Targets.groupQrCodeEntry, LONG_WAIT_TIMEOUT);
    }

    @Override
    public void execute() {
    }
}
