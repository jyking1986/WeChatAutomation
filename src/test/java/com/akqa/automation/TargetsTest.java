package com.akqa.automation;

import com.akqa.automation.qrcode.ImageHelper;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Mouse;
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

    @Test
    @Ignore
    public void testLocateTarget() {
        ScreenRegion user1 = mainScreenRegion.find(Targets.user1Snapshot);
        mouse.click(user1.getCenter());
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

    protected void saveQRCode(final BufferedImage capture, final String fileName) throws IOException {
        File output = new File(fileName + "." + EXTENSION);
        ImageIO.write(capture, EXTENSION, output);
    }
}
