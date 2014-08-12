package com.akqa.automation.task;

import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 6:36 PM
 */
public class ExportNewQRCodeTaskTest {
    protected final ScreenRegion mainScreenRegion = new DesktopScreenRegion();
    protected final Mouse mouse = new DesktopMouse();

    @Test
    @Ignore
    public void testExecute() throws Exception {
        ExportNewQRCodeTask exportNewQRCodeTask = new ExportNewQRCodeTask(15);
        exportNewQRCodeTask.execute();
    }

    @Test
    @Ignore
    public void testScroll() throws InterruptedException {
        Thread.sleep(2000);
        mouse.click(mainScreenRegion.getRelativeScreenLocation(600, 600));
        mouse.drag(mainScreenRegion.getRelativeScreenLocation(600, 600));
        mouse.drop(mainScreenRegion.getRelativeScreenLocation(600, 300));
    }

    @Test
    @Ignore
    public void testWheel() throws InterruptedException {
        Thread.sleep(2000);
        mouse.wheel(1, 1);
    }
}
