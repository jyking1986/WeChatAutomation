package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import com.akqa.automation.qrcode.ImageHelper;
import org.sikuli.api.ScreenRegion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 10:58 AM
 */
public class CreateGroupTask extends TaskBase {
    private final int groupCount;
    private final NRCClient nrcClient;

    public CreateGroupTask(final int groupCount, final NRCClient nrcClient) {
        super("create-group");
        this.groupCount = groupCount;
        this.nrcClient = nrcClient;
    }

    @Override
    public void execute() {
        int i = 0;
        while (i < groupCount) {

            try {
                backHome();
                clickTarget(Targets.create, SHORT_WAIT_TIMEOUT);
                clickTarget(Targets.createGroup, LONG_WAIT_TIMEOUT);
                clickTarget(Targets.createGroupFlag, LONGER_WAIT_TIMEOUT);
                int tryLimit = 5;
                while (mainScreenRegion.wait(Targets.firstSelect, LONG_WAIT_TIMEOUT) == null) {
                    clickTarget(Targets.user1Snapshot, LONG_WAIT_TIMEOUT);
                    if (tryLimit-- < 0) {
                        break;
                    }
                }
                tryLimit = 5;
                while (mainScreenRegion.wait(Targets.select2User, LONG_WAIT_TIMEOUT) == null) {
                    clickTarget(Targets.user2Snapshot, LONG_WAIT_TIMEOUT);
                    if (tryLimit-- < 0) {
                        break;
                    }
                }

                clickTarget(Targets.confirmGroupCreation, SHORT_WAIT_TIMEOUT);
                System.out.println(String.format("%s Creating WeChat group", i));
                clickTarget(Targets.groupEntry, LONGER_WAIT_TIMEOUT);
                ScreenRegion contact = mainScreenRegion.wait(Targets.contactNotSaved, LONG_WAIT_TIMEOUT);
                if (contact != null) {
                    mouse.doubleClick(contact.getRelativeScreenLocation(1200, 25));
                }
                clickTarget(Targets.groupQrCodeEntry, LONG_WAIT_TIMEOUT);
                ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, LONGER_WAIT_TIMEOUT);
                if (qrCode != null) {
                    Rectangle bounds = qrCode.getBounds();
                    BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, QR_CODE_WIDTH, QR_CODE_HEIGHT);
                    try {
                        System.out.println(String.format("%s Generating WeChat group", i));
                        java.util.List<String> links = new ArrayList<>();
                        links.add(ImageHelper.extractContentFromQRCode(capture));
                        nrcClient.addNewQRCodeLinks(links);
                        i++;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }

    }
}
