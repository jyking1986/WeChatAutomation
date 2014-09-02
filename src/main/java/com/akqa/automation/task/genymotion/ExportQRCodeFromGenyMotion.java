package com.akqa.automation.task.genymotion;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import com.akqa.automation.qrcode.ImageHelper;
import com.akqa.automation.task.Task;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tech
 * Date: 21/08/2014
 * Time: 18:00
 */
public class ExportQRCodeFromGenyMotion implements Task {
    public static final int TIMEOUT = 1000;
    protected final ScreenRegion mainScreenRegion = new DesktopScreenRegion();
    protected final Mouse mouse = new DesktopMouse();
    private final NRCClient client;
    private final int count;
    private final int offset;

    public ExportQRCodeFromGenyMotion(NRCClient client, int count, int offset) {
        this.client = client;
        this.count = count + offset;
        this.offset = offset;
    }

    @Override
    public void execute() {
        int index = offset;
        while (index <= count) {
            backHome();
            try {
                ScreenRegion main = backHome();
                ScreenRegion topBar = enterContact(main);
                locateGroup(index, topBar);
                enterGroupDetail();
                enterQRCodePage();
                exportQRCode();
                log("Export group QR code for crew : %s", index++);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                log("Failed to locate group and export group QR Code.");
            }
        }
    }

    ScreenRegion backHome() {
        ScreenRegion mainNav = mainScreenRegion.wait(Targets.main_nav, TIMEOUT);
        int tryLimit = 5;
        while (mainNav == null && tryLimit-- > 0) {
            ScreenRegion nav = mainScreenRegion.wait(Targets.wechat_nav, TIMEOUT);
            if (nav != null) {
                mouse.doubleClick(nav.getCenter());
            }
            mainNav = mainScreenRegion.wait(Targets.main_nav, TIMEOUT);
        }

        checkNotNull(mainNav);
        mouse.click(mainNav.getCenter());

        return mainNav;
    }

    ScreenRegion enterContact(ScreenRegion home) {
        checkNotNull(home);
        mouse.click(home.getRelativeScreenLocation(170, 720));

        ScreenRegion groupEntry = mainScreenRegion.wait(Targets.group_contact_entry, TIMEOUT);
        mouse.click(groupEntry.getCenter());
        ScreenRegion contactList = mainScreenRegion.wait(Targets.confirm_group_contact_list, TIMEOUT);
        checkNotNull(contactList);

        return contactList;
    }

    void locateGroup(final int index, ScreenRegion topBar) {
        cutHead(topBar);
        int pageIndex = (int) Math.floor((index - 1) / 10d);
        for (int i = 0; i < pageIndex; i++) {
            gotoPage(topBar);
        }
        int indexInsidePage = index % 10;
        indexInsidePage = indexInsidePage == 0 ? 10 : indexInsidePage;
        mouse.click(topBar.getRelativeScreenLocation(300, 80 + 68 * (indexInsidePage - 1)));
    }

    void enterGroupContactSnapshot() {
        ScreenRegion topBar = mainScreenRegion.wait(Targets.confirm_group_contact_snapshot, TIMEOUT);
        checkNotNull(topBar);

        ScreenRegion enterGroup = mainScreenRegion.wait(Targets.enter_group_button, TIMEOUT);
        if (enterGroup == null) {
            cutHead(topBar);
            mouse.wheel(-1, 40);
            enterGroup = mainScreenRegion.wait(Targets.enter_group_button, TIMEOUT);
            checkNotNull(enterGroup);
        }

        mouse.click(enterGroup.getCenter());
    }

    void gotoPage(ScreenRegion topBar) {
        ScreenLocation from = topBar.getRelativeScreenLocation(300, 730);
        mouse.drag(from);
        mouse.drop(Relative.to(from).above(707).getScreenLocation());
    }

    void cutHead(ScreenRegion topBar) {
        ScreenLocation from = topBar.getRelativeScreenLocation(300, 300);
        mouse.drag(from);
        mouse.drop(Relative.to(from).above(44).getScreenLocation());
    }

    void enterGroupDetail() {
        ScreenRegion groupDetailEntry = mainScreenRegion.wait(Targets.group_detail_entry_button, TIMEOUT * 4);
        checkNotNull(groupDetailEntry);
        mouse.click(groupDetailEntry.getCenter());
    }

    void enterQRCodePage() {
        ScreenRegion topBar = mainScreenRegion.wait(Targets.confirm_group_detail, TIMEOUT);
        checkNotNull(topBar);

        cutHead(topBar);
        ScreenRegion qrCodeBar = mainScreenRegion.wait(Targets.qr_code_bar, TIMEOUT);
        if (qrCodeBar == null) {
            ScreenRegion bottom = mainScreenRegion.wait(Targets.groupDetailBottom, TIMEOUT);
            int retry = 5;
            while (retry-- > 0 && bottom == null) {
                mouse.wheel(-1, 20);
                bottom = mainScreenRegion.wait(Targets.groupDetailBottom, TIMEOUT);
            }

            checkNotNull(bottom);
            ScreenLocation from = topBar.getRelativeScreenLocation(300, 200);
            mouse.drag(from);
            mouse.drop(Relative.to(from).below(400).getScreenLocation());
            qrCodeBar = mainScreenRegion.wait(Targets.qr_code_bar, TIMEOUT);
        }

        mouse.click(qrCodeBar.getCenter());
    }

    void exportQRCode() throws FormatException, ChecksumException, NotFoundException, IOException {
        ScreenRegion topBar = mainScreenRegion.wait(Targets.confirm_qr_code_detail, TIMEOUT * 2);
        checkNotNull(topBar);
        ScreenRegion qrCode = mainScreenRegion.wait(Targets.qr_code_corner, TIMEOUT * 8);
        checkNotNull(qrCode);
        Rectangle bounds = qrCode.getBounds();
        BufferedImage capture = mainScreenRegion.getScreen().getScreenshot(bounds.x, bounds.y, 285, 285);
        saveQRCode(capture, "test");
        String link = ImageHelper.extractContentFromQRCode(capture);
        log("Exporting qr code link: %s", link);
        client.refreshQRCodeLink(link);
    }

    protected void saveQRCode(final BufferedImage capture, final String fileName) throws IOException {
        File output = new File(fileName + ".jpg");
        ImageIO.write(capture, "jpg", output);
    }

    private void log(String message, Object... args) {
        System.out.println(String.format(message, args));
    }

    @Override
    public String getName() {
        return "export-through-genymotion";
    }
}
