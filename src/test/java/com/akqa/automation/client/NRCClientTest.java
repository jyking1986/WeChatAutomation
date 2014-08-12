package com.akqa.automation.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/12/14
 * Time: 6:43 PM
 */
public class NRCClientTest {
    @Test
    public void testAddNewQRCodeLinks() throws Exception {

    }

    @Test
    @Ignore
    public void testUpdateQRCodeLink() {
        List<String> links = new ArrayList<>();
        links.add("http://weixin.qq.com/g/AQV4Z9VnkqINGCG/1");
        links.add("http://weixin.qq.com/g/AX13Kqoz4c68O+aB1");

        ClientResponse response = Client.create().resource("http://localhost:8080/api/group/qrcode/link")
                .header("Authorization", "Basic cm9vdDpyb290")
                .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, links);

        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
    }
}
