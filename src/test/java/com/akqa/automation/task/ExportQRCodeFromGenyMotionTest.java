package com.akqa.automation.task;

import com.akqa.automation.client.NRCClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sikuli.api.ScreenRegion;

/**
 * Created with IntelliJ IDEA.
 * User: tech
 * Date: 21/08/2014
 * Time: 18:32
 */
public class ExportQRCodeFromGenyMotionTest {
    private ExportQRCodeFromGenyMotion task;

    @Before
    public void setUp() throws Exception {
//        String server = "114.215.189.62";
        String server = "runclub.nike.com.cn";
        NRCClient client = new NRCClient(server);
        task = new ExportQRCodeFromGenyMotion(client, 21, 166);
    }

    @Test
    @Ignore
    public void testExecute() throws Exception {
        task.execute();
    }

    @Test
    @Ignore
    public void testBackHome() throws Exception {
        task.backHome();
    }

    @Test
    @Ignore
    public void testEnterContact() throws Exception {
        ScreenRegion main = task.backHome();
        task.enterContact(main);
    }

    @Test
    public void testLocateGroup() throws Exception {

    }

    @Test
    @Ignore
    public void testCutHead() throws Exception {
        ScreenRegion main = task.backHome();
        ScreenRegion topBar = task.enterContact(main);
        task.cutHead(topBar);
    }

    @Test
    @Ignore
    public void testLocate() throws Exception {
        ScreenRegion main = task.backHome();
        ScreenRegion topBar = task.enterContact(main);
        task.locateGroup(11, topBar);
        task.enterGroupContactSnapshot();
        task.enterGroupDetail();
        task.enterQRCodePage();
        task.exportQRCode();
    }

    @Test
    public void testEnterGroupDetail() throws Exception {

    }

    @Test
    public void testExportQRCode() throws Exception {

    }
}
