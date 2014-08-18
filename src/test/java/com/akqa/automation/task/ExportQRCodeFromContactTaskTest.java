package com.akqa.automation.task;

import com.akqa.automation.Targets;
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
    Canvas canvas = new DesktopCanvas();

    @Test
    public void testExecute() throws Exception {

    }

    @Test
    public void testNextPage() throws InterruptedException {
        ScreenRegion startConvesation = mainScreenRegion.wait(Targets.groupContactList, 1000);

        canvas.addBox(startConvesation);

        mouse.click(startConvesation.getCenter());

        ScreenLocation start = startConvesation.getRelativeScreenLocation(300, 680);
//        mouse.click(start);
        mouse.drag(start);
        mouse.drop(Relative.to(start).above(80).getScreenLocation());
    }
}
