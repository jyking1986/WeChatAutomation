package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import com.akqa.automation.qrcode.ImageHelper;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: tech
 * Date: 14/08/2014
 * Time: 22:55
 */
public class ExportQRCodeFromContactTask extends TaskBase {
    private final int groupCount;
    private final NRCClient nrcClient;

    public ExportQRCodeFromContactTask(final int groupCount, final NRCClient nrcClient) {
        super("export-from-contact");
        this.groupCount = groupCount;
        this.nrcClient = nrcClient;
    }

    @Override
    public void execute() {
        int i = 1;
        while (i <= groupCount) {

            try {
                if (executeImp(i)) {
                    System.out.println(String.format("Exporting %s group.", i));
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ScreenRegion error = mainScreenRegion.wait(Targets.notResponseError, LONG_WAIT_TIMEOUT);
                if (error != null) {
                    clickTarget(Targets.wait, LONG_WAIT_TIMEOUT);
                }
            }


        }
    }

    private boolean executeImp(int index) throws InterruptedException {
        ScreenRegion mainScreen = mainScreenRegion.wait(Targets.mainScreen, LONG_WAIT_TIMEOUT);
        int retryLimit = 5;
        while (mainScreen == null && retryLimit-- > 0) {
            clickTarget(Targets.wechatNav, SHORT_WAIT_TIMEOUT);
            mainScreen = mainScreenRegion.wait(Targets.mainScreen, LONG_WAIT_TIMEOUT);
        }
        if (mainScreen == null) return false;

        mouse.click(mainScreen.getRelativeScreenLocation(1000, 75));
        clickTarget(Targets.contactGroupEntry, LONGER_WAIT_TIMEOUT);

        locateGroup(index);

        if (enterGroup()) {
            if (exportQRCode()) {
                return true;
            }
        }

        return false;
    }

    protected boolean exportQRCode() {
        ScreenRegion groupEntry = mainScreenRegion.wait(Targets.groupEntry, LONGER_WAIT_TIMEOUT);
        if (groupEntry != null) {
            mouse.doubleClick(groupEntry.getCenter());
        } else {
            System.out.println("saveContact: Error in open group detail.");
        }
        ScreenRegion chatInformation = mainScreenRegion.wait(Targets.chatInformation, LONGER_WAIT_TIMEOUT);
        mouse.click(chatInformation.getRelativeScreenLocation(10, 60));
        ScreenRegion qrCodeEntry = mainScreenRegion.wait(Targets.groupQrCodeEntry, SHORT_WAIT_TIMEOUT);
        if (qrCodeEntry == null) {
            mouse.wheel(1, 40);
            ScreenRegion home = mainScreenRegion.find(Targets.home);
            ScreenLocation bottom = home.getRelativeScreenLocation(20, -60);
            ScreenLocation top = Relative.to(bottom).above(500).getScreenLocation();
            mouse.drag(top);
            mouse.drop(Relative.to(top).below(250).getScreenLocation());
            qrCodeEntry = mainScreenRegion.wait(Targets.groupQrCodeEntry, SHORT_WAIT_TIMEOUT);
        }
        if (qrCodeEntry != null) {
            mouse.click(qrCodeEntry.getCenter());
            ScreenRegion qrCode = mainScreenRegion.wait(Targets.qrCodeCorner, LONGER_WAIT_TIMEOUT);
            if (qrCode != null) {
                Rectangle bounds = qrCode.getBounds();
                BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, QR_CODE_WIDTH, QR_CODE_HEIGHT);
                try {
                    java.util.List<String> links = new ArrayList<>();
                    links.add(ImageHelper.extractContentFromQRCode(capture));
                    nrcClient.refreshQRCodeLinks(links);
                    System.out.println(String.format("Refresh WeChat Group QR Code."));
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println("saveContact: failed to locate qr code entry button.");
        }

        return false;

    }

    protected boolean enterGroup() {
        ScreenRegion screenRegion = mainScreenRegion.wait(Targets.groupDetail, LONGER_WAIT_TIMEOUT);
        mouse.click(screenRegion.getRelativeScreenLocation(300, 20));
        ScreenRegion enterGroup = mainScreenRegion.wait(Targets.enterGroup, SHORT_WAIT_TIMEOUT);
        int tryLimit = 5;
        while (enterGroup == null && tryLimit-- > 0) {
            mouse.wheel(1, 10);
            enterGroup = mainScreenRegion.wait(Targets.enterGroup, SHORT_WAIT_TIMEOUT);
        }
        if (enterGroup != null) {
            mouse.click(enterGroup.getCenter());
            return true;
        }
        return false;
    }

    protected void locateGroup(int index) throws InterruptedException {
        ScreenRegion startConversation = mainScreenRegion.wait(Targets.groupContactList, LONGER_WAIT_TIMEOUT);
        mouse.click(startConversation.getRelativeScreenLocation(300, 20));
        locatePage(index, startConversation);
        int i = index % 7;
        i = (i == 0 ? 7 : i);
        mouse.click(startConversation.getRelativeScreenLocation(300, i * 52 + 56));
    }

    protected void locatePage(int index, ScreenRegion startConversation) throws InterruptedException {
        int pageIndex = (index - 1) / 7;
        for (int i = 0; i < pageIndex; i++) {
            ScreenLocation start = startConversation.getRelativeScreenLocation(300, 680);
            mouse.drag(start);
            mouse.drop(Relative.to(start).above(604).getScreenLocation());
            Thread.sleep(1000);
        }
    }

}
