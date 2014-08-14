package com.akqa.automation.task;

import com.akqa.automation.Targets;
import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 4:06 PM
 */
public class SaveGroupToContactTaskTest extends TaskBase {
    public SaveGroupToContactTaskTest() {
        super("test");
    }

    @Test
    @Ignore
    public void testExecute() throws Exception {
        SaveGroupToContactTask saveGroupToContactTask = new SaveGroupToContactTask(200);
        saveGroupToContactTask.execute();
    }

    @Test
    @Ignore
    public void testMouseWheel() throws Exception {
        Thread.sleep(2000);
        clickTarget(Targets.groupMemberSummary, LONG_WAIT_TIMEOUT);
//        System.out.println("Located target: groupMemberSummary");
//        mouse.wheel(1, 10);
//        System.out.println("Mouse wheel");
        ScreenRegion screenRegion = mainScreenRegion.find(Targets.home);
        ScreenLocation from = screenRegion.getRelativeScreenLocation(20, -60);
//        mouse.click(from);
        ScreenLocation to = Relative.to(from).above(500).getScreenLocation();
//        for (int i = 0; i < 7; i++) {
//
//        }
        mouse.drag(from);
        mouse.drop(to);
        mouse.wheel(1, 100);
        clickTarget(Targets.contactNotSaved, LONG_WAIT_TIMEOUT);
    }

    @Override
    public void execute() {
    }
}
