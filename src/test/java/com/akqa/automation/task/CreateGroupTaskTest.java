package com.akqa.automation.task;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 12:32 PM
 */
public class CreateGroupTaskTest {
    @Test
    @Ignore
    public void testExecute() throws Exception {
        CreateGroupTask createGroupTask = new CreateGroupTask(2);
        createGroupTask.execute();
    }
}
