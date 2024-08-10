package com.mddb.integration;

import com.mddb.controller.MainController;
import com.mddb.usecase.DeviceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MainController.class})
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DeviceService deviceService;

    @Test
    @SneakyThrows
    public void testGetDevices() {
        MvcResult result = mockMvc.perform(get("/phones")
                .param("pageNumber", "0")
                .param("pageSize", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"))
                .andReturn();
    }

}
