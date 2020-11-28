package com.example.challenge;

import com.example.challenge.controller.Controller;
import com.example.challenge.controller.OrderController;
import com.example.challenge.repository.OrderItemRepository;
import com.example.challenge.repository.OrderRepository;
import com.example.challenge.service.OrderService;
import com.example.challenge.service.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OrderController.class)
public class OrderTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save()  throws Exception {

        String order = "{ \"description\":\"Discount5\",\n" +
                "    \"products\": [\n" +
                "                    [\"Computer\", \"2\"],\n" +
                "                    [\"Car\", \"1\"],\n" +
                "                    [\"Service\", \"1\"],\n" +
                "                    [\"Outra\", \"1000\"]\n" +
                "                ],\n" +
                "    \"discount\":\"5.00F\"}";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/orders/save")
                .accept(MediaType.APPLICATION_JSON)
                .content(order)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
    }

    @Test
    public void teste() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/orders/teste")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
