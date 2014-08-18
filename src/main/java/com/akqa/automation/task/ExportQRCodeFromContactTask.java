package com.akqa.automation.task;

import com.akqa.automation.Targets;
import com.akqa.automation.client.NRCClient;
import org.sikuli.api.ScreenRegion;

/**
 * Created with IntelliJ IDEA.
 * User: tech
 * Date: 14/08/2014
 * Time: 22:55
 */
public class ExportQRCodeFromContactTask extends TaskBase {
    private final int groupCount;
    private final NRCClient nrcClient;

    public ExportQRCodeFromContactTask(final int groupCount, final NRCClient nrcClient) {
        super("export-from-contact");
        this.groupCount = groupCount;
        this.nrcClient = nrcClient;
    }

    @Override
    public void execute() {
        int i = 0;
        while (i < groupCount) {

            try {
                if (executeImp(i)) {
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ScreenRegion error = mainScreenRegion.wait(Targets.notResponseError, LONG_WAIT_TIMEOUT);
                if (error != null) {
                    clickTarget(Targets.wait, LONG_WAIT_TIMEOUT);
                }
            }


        }
    }

    private boolean executeImp(int index) {
        backHome();
        clickTarget(Targets.create, SHORT_WAIT_TIMEOUT);
        clickTarget(Targets.createGroup, LONG_WAIT_TIMEOUT);
        clickTarget(Targets.createGroupFlag, LONGER_WAIT_TIMEOUT);
        clickTarget(Targets.chooseAGroup, LONG_WAIT_TIMEOUT);
        clickTarget(Targets.startAGroupConvesation, LONG_WAIT_TIMEOUT);

        return false;
    }

    private void nextPage() {

    }

}
