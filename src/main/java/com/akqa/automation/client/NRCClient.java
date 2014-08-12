package com.akqa.automation.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 6:13 PM
 */
public class NRCClient {
    private static final Logger log = LoggerFactory.getLogger(NRCClient.class);
    private final String server;
    private final Client client;

    public NRCClient(String server) {
        this.server = server;
        client = Client.create();
    }

    public void addNewQRCodeLinks(final List<String> links) {
        final String url = String.format("http://%s/api/group/qrcode/link", server);

        ClientResponse response = client.resource(url).header("Authorization", "Basic cm9vdDpyb290")
                .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, links);

        log.info(response.getEntity(String.class));
    }
}
