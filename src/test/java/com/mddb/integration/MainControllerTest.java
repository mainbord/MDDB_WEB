package com.mddb.integration;

import com.mddb.usecase.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class MainControllerTest {

    @MockitoBean
    DeviceService deviceService;

    @Autowired
    private RestTestClient restTestClient;

    @LocalServerPort
    private int port;

    @Test
    public void testGetDevices() {
        restTestClient.get()
                .uri("http://localhost:%d/phones".formatted(port))
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[]");
    }

}
