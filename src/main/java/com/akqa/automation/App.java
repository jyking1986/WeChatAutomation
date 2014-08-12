package com.akqa.automation;

import com.akqa.automation.client.NRCClient;
import com.akqa.automation.task.CreateGroupTask;
import com.akqa.automation.task.ExportQRCodeTask;
import com.akqa.automation.task.SaveGroupToContactTask;
import com.akqa.automation.task.Task;

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
        NRCClient nrcClient = new NRCClient("114.215.189.62");
        Task createGroupTask = new CreateGroupTask(5, nrcClient);
        taskMap.put(createGroupTask.getName(), createGroupTask);
        Task exportQRCodeTask = new ExportQRCodeTask(nrcClient, 40);
        taskMap.put(exportQRCodeTask.getName(), exportQRCodeTask);
        Task saveGroupToContactTask = new SaveGroupToContactTask(40);
        taskMap.put(saveGroupToContactTask.getName(), saveGroupToContactTask);

        if (args.length != 1 || !taskMap.containsKey(args[0])) {
            System.out.println("Only Support the following commands:");
            for (String key : taskMap.keySet()) {
                System.out.println(key);
            }
            return;
        }

        taskMap.get(args[0]).execute();

    }
}
