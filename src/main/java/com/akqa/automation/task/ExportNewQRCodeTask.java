package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.qrcode.ImageHelper;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 6:29 PM
 */
public class ExportNewQRCodeTask extends TaskBase {
    private final int totalCrewNumber;

    public ExportNewQRCodeTask(int totalCrewNumber) {
        this.totalCrewNumber = totalCrewNumber;
    }

    @Override
    public void execute() {
        backHome();
        int step = 0;
        while (true) {
            step++;
            for (int j = 0; j < step; j++) {
                keyboard.type(Key.DOWN);
            }
            keyboard.type(Key.ENTER);
            ScreenRegion groupEntryRegion = mainScreenRegion.wait(Targets.groupEntry, LONG_WAIT_TIMEOUT);
            if (groupEntryRegion != null) {
                mouse.click(groupEntryRegion.getCenter());
                ScreenRegion qrCodeEntryRegion = mainScreenRegion.wait(Targets.groupQrCodeEntry, LONG_WAIT_TIMEOUT);
                if (qrCodeEntryRegion == null) continue;
                mouse.click(qrCodeEntryRegion.getCenter());
                ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, LONGER_WAIT_TIMEOUT);
                if (qrCode != null) {
                    Rectangle bounds = qrCode.getBounds();
                    BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, 200, 200);
                    try {
                        String url = ImageHelper.extractContentFromQRCode(capture);
                        System.out.println(url);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            }
        }

        for (int i = 1; i < totalCrewNumber; i++) {
            backHome();
            ScreenRegion charTabRegion = mainScreenRegion.wait(Targets.chatTab, SHORT_WAIT_TIMEOUT);
            if (charTabRegion != null) {
                mouse.click(charTabRegion.getCenter());
            }
            try {
                keyboard.type(Key.DOWN);
                keyboard.type(Key.DOWN);
                Thread.sleep(1000);
                keyboard.type(Key.ENTER);
            } catch (InterruptedException e) {

            }
            ScreenRegion groupEntryRegion = mainScreenRegion.wait(Targets.groupEntry, LONG_WAIT_TIMEOUT);
            if (groupEntryRegion != null) {
                mouse.click(groupEntryRegion.getCenter());
                ScreenRegion qrCodeEntryRegion = mainScreenRegion.wait(Targets.groupQrCodeEntry, LONG_WAIT_TIMEOUT);
                if (qrCodeEntryRegion == null) continue;
                mouse.click(qrCodeEntryRegion.getCenter());
                ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, LONGER_WAIT_TIMEOUT);
                if (qrCode != null) {
                    Rectangle bounds = qrCode.getBounds();
                    BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, 200, 200);
                    try {
                        String url = ImageHelper.extractContentFromQRCode(capture);
                        System.out.println(url);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        }
    }
}
