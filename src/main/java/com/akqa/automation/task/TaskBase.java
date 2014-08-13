package com.akqa.automation.task;

import com.akqa.automation.Targets;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 11:00 AM
 */
public abstract class TaskBase implements Task {
    public static final String EXTENSION = "jpg";
    protected final static int QR_CODE_WIDTH = 200;
    protected final static int QR_CODE_HEIGHT = 200;
    protected final static int SHORT_WAIT_TIMEOUT = 300;
    protected final static int LONG_WAIT_TIMEOUT = 2000;
    protected final static int LONGER_WAIT_TIMEOUT = 8000;
    protected final ScreenRegion mainScreenRegion = new DesktopScreenRegion();
    protected final Mouse mouse = new DesktopMouse();
    protected final Keyboard keyboard = new DesktopKeyboard();
    private final String name;

    protected TaskBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    protected ScreenRegion backHome() {
        ScreenRegion homeRegion = null;
        int retry = 5;
        while (homeRegion == null && retry > 0) {
            retry--;
            clickTarget(Targets.wechatNav, SHORT_WAIT_TIMEOUT);
            homeRegion = mainScreenRegion.wait(Targets.chatTab, SHORT_WAIT_TIMEOUT);
        }

        checkNotNull(homeRegion);

        System.out.println("backing home");
        return homeRegion;
    }

    protected void clickTarget(final Target target, int shortWaitTimeout) {
        checkNotNull(target);
        ScreenRegion targetRegion = mainScreenRegion.wait(target, shortWaitTimeout);
        checkNotNull(targetRegion);


        mouse.click(targetRegion.getCenter());
    }

    protected void saveQRCode(final BufferedImage capture, final String fileName) throws IOException {
        File output = new File(fileName + "." + EXTENSION);
        ImageIO.write(capture, EXTENSION, output);
    }
}
