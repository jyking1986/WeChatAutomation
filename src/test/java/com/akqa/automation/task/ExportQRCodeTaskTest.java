package com.akqa.automation.task;

import com.akqa.automation.client.NRCClient;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 4:49 PM
 */
public class ExportQRCodeTaskTest {
    @Test
    @Ignore
    public void testExecute() throws Exception {
        NRCClient nrcClient = new NRCClient("localhost:8080");
        ExportQRCodeTask exportQRCodeTask = new ExportQRCodeTask(nrcClient, 15);
        exportQRCodeTask.execute();
    }
}
