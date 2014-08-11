package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.qrcode.ImageHelper;
import org.sikuli.api.ScreenRegion;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 10:58 AM
 */
public class CreateGroupTask extends TaskBase {
    private final int groupCount;

    public CreateGroupTask(final int groupCount) {
        this.groupCount = groupCount;
    }

    @Override
    public void execute() {
        for (int i = 0; i < groupCount; i++) {
            backHome();

            clickTarget(Targets.create, SHORT_WAIT_TIMEOUT);
            clickTarget(Targets.createGroup, LONG_WAIT_TIMEOUT);
            clickTarget(Targets.user1Snapshot, LONG_WAIT_TIMEOUT);
            clickTarget(Targets.user2Snapshot, LONG_WAIT_TIMEOUT);

            ScreenRegion confirmSelect2User = mainScreenRegion.wait(Targets.select2User, SHORT_WAIT_TIMEOUT);
            if (confirmSelect2User != null) {
                clickTarget(Targets.confirmGroupCreation, SHORT_WAIT_TIMEOUT);
                clickTarget(Targets.groupEntry, LONG_WAIT_TIMEOUT);
                clickTarget(Targets.groupQrCodeEntry, LONG_WAIT_TIMEOUT);
                ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, LONGER_WAIT_TIMEOUT);
                if (qrCode != null) {
                    Rectangle bounds = qrCode.getBounds();
                    BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, QR_CODE_WIDTH, QR_CODE_HEIGHT);
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
