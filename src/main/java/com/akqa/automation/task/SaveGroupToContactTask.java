package com.akqa.automation.task;

import com.akqa.automation.Targets;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Key;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 10:03 AM
 */
public class SaveGroupToContactTask extends TaskBase {

    private final int count;

    public SaveGroupToContactTask(int count) {
        super("save-to-contact");
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

        int i = 0;
        while (i < count) {
            keyboard.type(Key.DOWN + Key.DOWN);
            keyboard.type(Key.ENTER);

            if (executeImp()) {
                i++;
            }

            backHome();
        }
    }

    private boolean executeImp() {
        try {
            clickTarget(Targets.groupEntry, LONGER_WAIT_TIMEOUT);
            clickTarget(Targets.groupMemberSummary, LONGER_WAIT_TIMEOUT);
            ScreenRegion home = mainScreenRegion.find(Targets.home);
            ScreenLocation bottom = home.getRelativeScreenLocation(20, -60);
            ScreenLocation top = Relative.to(bottom).above(500).getScreenLocation();
            mouse.drag(bottom);
            mouse.drop(top);
            mouse.wheel(1, 30);
            ScreenRegion contact = mainScreenRegion.wait(Targets.contactNotSaved, SHORT_WAIT_TIMEOUT);
            if (contact != null) {
                mouse.doubleClick(contact.getRelativeScreenLocation(1200, 25));
                System.out.println("Group be saved.");
            } else {
                System.out.println("Already be saved.");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Not a group." + e.getMessage());
            ScreenRegion error = mainScreenRegion.wait(Targets.notResponseError, LONG_WAIT_TIMEOUT);
            if (error != null) {
                clickTarget(Targets.wait, LONG_WAIT_TIMEOUT);
            }
            return false;
        }
    }
}
