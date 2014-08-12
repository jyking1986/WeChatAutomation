package com.akqa.automation.task;

import com.akqa.automation.Targets;
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
        for (int i = 0; i < count; i++) {
            keyboard.type(Key.DOWN + Key.DOWN);
            keyboard.type(Key.ENTER);

            try {
                clickTarget(Targets.groupEntry, LONG_WAIT_TIMEOUT);
                ScreenRegion contact = mainScreenRegion.wait(Targets.contactNotSaved, LONG_WAIT_TIMEOUT);
                mouse.doubleClick(contact.getRelativeScreenLocation(1200, 20));
            } catch (Exception e) {
                System.out.println("Not a group.");
            }

            backHome();
        }
    }
}
