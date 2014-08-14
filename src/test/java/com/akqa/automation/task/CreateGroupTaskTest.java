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
        String server = "runclub.nike.com.cn";
//        String server = "114.215.189.62";
        NRCClient nrcClient = new NRCClient(server);
        CreateGroupTask createGroupTask = new CreateGroupTask(100, nrcClient);
        createGroupTask.execute();
    }
}
