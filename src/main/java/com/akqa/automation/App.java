package com.akqa.automation;

import com.akqa.automation.client.NRCClient;
import com.akqa.automation.task.CreateGroupTask;
import com.akqa.automation.task.ExportQRCodeTask;
import com.akqa.automation.task.SaveGroupToContactTask;
import com.akqa.automation.task.Task;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 10:26 AM
 */
public class App {

    public static void main(String[] args) {
        Map<String, Task> taskMap = new HashMap<>();
        NRCClient nrcClient = new NRCClient("114.215.189.62");
        Task createGroupTask = new CreateGroupTask(5, nrcClient);
        taskMap.put(createGroupTask.getName(), createGroupTask);
        Task exportQRCodeTask = new ExportQRCodeTask(nrcClient, 40);
        taskMap.put(exportQRCodeTask.getName(), exportQRCodeTask);
        Task saveGroupToContactTask = new SaveGroupToContactTask(40);
        taskMap.put(saveGroupToContactTask.getName(), saveGroupToContactTask);

        if (args.length < 1) {
            System.out.println("Only Support the following commands:");
            for (String key : taskMap.keySet()) {
                System.out.println(key);
            }
            return;
        }
        String command = args[0];
        if (taskMap.containsKey(command)) {
            taskMap.get(command).execute();
        } else if (command.equals("locate")) {
            File file = new File(args[1]);
            if (file.exists()) {
                System.out.println("locate file:" + file.getName());
                Target target = new ImageTarget(file);
                checkNotNull(target);
                ScreenRegion mainScreenRegion = new DesktopScreenRegion();
                Mouse mouse = new DesktopMouse();
                ScreenRegion screenRegion = mainScreenRegion.wait(target, 1000);
                mouse.click(screenRegion.getCenter());
            } else {
                System.out.println("cannot find image file.");
            }
        }

        System.exit(0);

    }
}
