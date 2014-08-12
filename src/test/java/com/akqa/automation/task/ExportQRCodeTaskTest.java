package com.akqa.automation.task;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 4:49 PM
 */
public class ExportQRCodeTaskTest {
    @Test
    public void testExecute() throws Exception {
        ExportQRCodeTask exportQRCodeTask = new ExportQRCodeTask(2);
        exportQRCodeTask.execute();
    }
}
