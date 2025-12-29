package com.mddb.client.impl;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import static org.junit.jupiter.api.Assertions.*;

class PhoneDbClientTest {

    @Test
    void getIdFromUrl() throws URISyntaxException {
        String testUrl = "https://phonedb.net/index.php?m=device&id=1410&c=compaq_pc_companion_c810c";
        URI uri = new URI(testUrl);
        String[] params = uri.getQuery().split("&");
        int id = 0;
        for (String param : params) {
            if (param.startsWith("id=")) {
                id = Integer.valueOf(param.substring(3)); // Убираем "id="
                System.out.println("ID из URL: " + id);
                break;
            }
        }
        System.out.println(id);
    }

}