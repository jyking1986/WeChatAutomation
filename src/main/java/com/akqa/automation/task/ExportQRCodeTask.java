package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import com.akqa.automation.qrcode.ImageHelper;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 4:32 PM
 */
public class ExportQRCodeTask extends TaskBase {
    private static final Logger log = LoggerFactory.getLogger(ExportQRCodeTask.class);
    private final NRCClient nrcClient;
    private final int count;

    public ExportQRCodeTask(final NRCClient nrcClient, final int count) {
        super("export-qr-code");
        this.nrcClient = nrcClient;
        this.count = count;
    }

    @Override
    public void execute() {
        backHome();

        ScreenRegion chatTab = mainScreenRegion.wait(Targets.chatTab, LONG_WAIT_TIMEOUT);
        mouse.click(chatTab.getCenter());
        mouse.doubleClick(chatTab.getCenter());
        mouse.click(chatTab.getRelativeScreenLocation(300, 70));
        mouse.click(mainScreenRegion.find(Targets.wechatNav).getCenter());
        java.util.List<String> links = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            keyboard.type(Key.DOWN + Key.DOWN);
            keyboard.type(Key.ENTER);

            try {
                clickTarget(Targets.groupEntry, LONG_WAIT_TIMEOUT);
                clickTarget(Targets.groupQrCodeEntry, LONG_WAIT_TIMEOUT);
                ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, LONGER_WAIT_TIMEOUT);
                if (qrCode != null) {
                    Rectangle bounds = qrCode.getBounds();
                    BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, QR_CODE_WIDTH, QR_CODE_HEIGHT);
                    try {
                        links.add(ImageHelper.extractContentFromQRCode(capture));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Not a group.");
            }

            backHome();

            if (links.size() > 20) {
                log.info(String.format("Refresh %s WeChat Group QR Code.", links.size()));
                nrcClient.refreshQRCodeLinks(links);
                links.clear();
            }
        }

        log.info(String.format("Refresh %s WeChat Group QR Code.", links.size()));
        if (links.size() > 0) {
            nrcClient.refreshQRCodeLinks(links);
        }
    }
}
