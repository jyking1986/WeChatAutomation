package com.akqa.automation;

import com.akqa.automation.qrcode.ImageHelper;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.*;
import org.sikuli.api.robot.Key;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 12:50 PM
 */
public class TargetsTest {
    public static final String EXTENSION = "jpg";
    protected final ScreenRegion mainScreenRegion = new DesktopScreenRegion();
    protected final Mouse mouse = new DesktopMouse();
    protected final Keyboard keyboard = new DesktopKeyboard();

    @Test
    @Ignore
    public void testLocateTarget() {
        ScreenRegion user1 = mainScreenRegion.find(Targets.user1Snapshot);
        mouse.click(user1.getCenter());

        ScreenRegion user2 = mainScreenRegion.find(Targets.user2Snapshot);
        mouse.click(user2.getCenter());
    }

    @Test
    @Ignore
    public void testLocateQRCodeCorner() throws FormatException, ChecksumException, NotFoundException, IOException {
        ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, 4000);
        Rectangle bounds = qrCode.getBounds();
        BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, 200, 200);
        saveQRCode(capture, "test");
        String url = ImageHelper.extractContentFromQRCode(capture);
        System.out.println(url);

    }

    @Test
    @Ignore
    public void testLocateContactTurnOn() throws InterruptedException {
        Thread.sleep(2000);
        ScreenRegion contact = mainScreenRegion.wait(Targets.contactNotSaved, 1000);
        mouse.doubleClick(contact.getRelativeScreenLocation(1200, 20));
    }

    @Test
    @Ignore
    public void testLocateTextTarget() throws InterruptedException {
        Thread.sleep(2000);
        Target textTarget = new TextTarget("Save to Contacts");
        ScreenRegion contact = mainScreenRegion.wait(textTarget, 600);
        mouse.click(contact.getCenter());
    }

    @Test
    @Ignore
    public void testLocateContact() throws InterruptedException {
        ScreenRegion contact = mainScreenRegion.wait(Targets.contactEntry, 600);
        mouse.click(contact.getCenter());
    }

    @Test
    @Ignore
    public void testLocateContactGroup() throws InterruptedException {
        ScreenRegion contact = mainScreenRegion.wait(Targets.contactGroupEntry, 600);
        mouse.click(contact.getCenter());
    }

    @Test
    @Ignore
    public void testGoNextPage() throws InterruptedException {
        ScreenRegion contact = mainScreenRegion.wait(Targets.chatTab, 600);
        ScreenLocation back = mainScreenRegion.find(Targets.back).getCenter();
        for (int i = 0; i < 10; i++) {
            mouse.doubleClick(contact.getCenter());
            for (int j = 0; j <= i; j++) {
                keyboard.type(Key.DOWN);
            }
            System.out.println(i);
            Thread.sleep(1000);
            keyboard.type(Key.ENTER);

            ScreenRegion mainRegion = mainScreenRegion.find(Targets.mainScreen);
            if (mainRegion == null) {
                mouse.click(back);
            }
        }
    }

    protected void saveQRCode(final BufferedImage capture, final String fileName) throws IOException {
        File output = new File(fileName + "." + EXTENSION);
        ImageIO.write(capture, EXTENSION, output);
    }
}
