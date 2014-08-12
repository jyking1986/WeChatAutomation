package com.akqa.automation.task;

import com.akqa.automation.client.NRCClient;
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
        NRCClient nrcClient = new NRCClient("localhost:8080");
        CreateGroupTask createGroupTask = new CreateGroupTask(2, nrcClient);
        createGroupTask.execute();
    }
}
