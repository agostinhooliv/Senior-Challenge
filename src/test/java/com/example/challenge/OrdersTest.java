package com.example.challenge;

import com.example.challenge.controller.OrderController;
import com.example.challenge.model.Order;
import com.example.challenge.service.OrderService;
import com.example.challenge.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(OrderController.class)
public class OrdersTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @MockBean
    private ProductService productService;

    @Test
    public void createOrUpdate() throws Exception {

        UUID idOrder = UUID.fromString("56c0a5b8-31df-4c6a-89b6-a8079c6ecda2");

        Order order = new Order();
        order.setIdOder(idOrder);
        order.setStatus('A');
        order.setDiscount(5.00F);
        order.setDescription("Descr");
        order.setDate(new Date());
        order.setBalance(300.00);

        Mockito.when(orderService.findById(idOrder)).thenReturn(Optional.of(order));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/orders/createOrUpdate")
                //Just for update
                .param("uuid", idOrder.toString())
                .accept(MediaType.APPLICATION_JSON)
                .content("{    \n" +
                        "    \"description\":\"Discount5\",\n" +
                        "    \"products\": [\n" +
                        "                    [\"Computer\", \"2\"],\n" +
                        "                    [\"Car\", \"1\"],\n" +
                        "                    [\"Service\", \"1\"],\n" +
                        "                    [\"Outra\", \"1000\"]\n" +
                        "                ],\n" +
                        "    \"discount\":\"5.00F\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void delete() throws Exception {

        UUID idOrder = UUID.fromString("db3f2916-86ca-4e63-864f-4d57b66fac77");

        Order order = new Order();
        order.setIdOder(idOrder);
        order.setStatus('A');
        order.setDiscount(5.00F);
        order.setDescription("Descr");
        order.setDate(new Date());
        order.setBalance(300.00);


        Mockito.when(orderService.findById(idOrder)).thenReturn(Optional.of(order));

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/orders/delete")
                .param("uuid", idOrder.toString());

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
