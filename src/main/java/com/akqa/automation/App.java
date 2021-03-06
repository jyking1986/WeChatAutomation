package com.akqa.automation;

import com.akqa.automation.client.NRCClient;
import com.akqa.automation.task.*;
import com.akqa.automation.task.genymotion.ExportQRCodeFromGenyMotion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 10:26 AM
 */
public class App {

    public static void main(String[] args) {
        Map<String, Task> taskMap = new HashMap<>();
//        String server = "114.215.189.62";
        String server = "runclub.nike.com.cn";

        NRCClient nrcClient = new NRCClient(server);

        Task createGroupTask = new CreateGroupTask(5, nrcClient);
        taskMap.put(createGroupTask.getName(), createGroupTask);
        Task exportQRCodeTask = new ExportQRCodeTask(nrcClient, 1700);
        taskMap.put(exportQRCodeTask.getName(), exportQRCodeTask);
        Task saveGroupToContactTask = new SaveGroupToContactTask(1700);
        taskMap.put(saveGroupToContactTask.getName(), saveGroupToContactTask);
        Task task = new ExportQRCodeFromContactTask(1700, nrcClient);
        taskMap.put(task.getName(), task);

        ExportQRCodeFromGenyMotion exportQRCodeFromGenyMotionTask = new ExportQRCodeFromGenyMotion(nrcClient, 1700, Integer.valueOf(System.getProperty("offset",""+1)));
        taskMap.put(exportQRCodeFromGenyMotionTask.getName(), exportQRCodeFromGenyMotionTask);

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
        }

        System.exit(0);

    }
}
