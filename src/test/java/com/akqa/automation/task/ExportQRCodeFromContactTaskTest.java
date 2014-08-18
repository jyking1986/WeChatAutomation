package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;

/**
 * Created with IntelliJ IDEA.
 * User: tech
 * Date: 18/08/2014
 * Time: 10:25
 */
public class ExportQRCodeFromContactTaskTest {
    protected final ScreenRegion mainScreenRegion = new DesktopScreenRegion();
    protected final Mouse mouse = new DesktopMouse();
    protected final Keyboard keyboard = new DesktopKeyboard();
    private Canvas canvas = new DesktopCanvas();
    private ExportQRCodeFromContactTask task;

    @Before
    public void setup() {
        NRCClient nrcClient = new NRCClient("114.215.189.62");
        task = new ExportQRCodeFromContactTask(10, nrcClient);
    }

    @Test
    @Ignore
    public void testExecute() throws Exception {
        task.execute();
    }

    @Test
    @Ignore
    public void testNextPage() throws InterruptedException {
        ScreenRegion startConvesation = mainScreenRegion.wait(Targets.groupContactList, 1000);
        canvas.addBox(startConvesation);
        mouse.click(startConvesation.getCenter());
        ScreenLocation start = startConvesation.getRelativeScreenLocation(300, 680);
        mouse.drag(start);
        mouse.drop(Relative.to(start).above(604).getScreenLocation());
    }

    @Test
    @Ignore
    public void testLocatePage() throws InterruptedException {
        ScreenRegion startConversation = mainScreenRegion.wait(Targets.groupContactList, 1000);
        mouse.click(startConversation.getCenter());
        task.locatePage(50, startConversation);
    }

    @Test
    @Ignore
    public void testExportGroup() throws InterruptedException {
        task.locateGroup(7);
    }

    @Test
    @Ignore
    public void testEnterGroup() {
        task.enterGroup();
    }

    @Test
    @Ignore
    public void testExportQRCode() {
        task.exportQRCode();
    }


}

